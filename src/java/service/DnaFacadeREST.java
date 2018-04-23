/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entidades.Dna;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mutantes.SecuenciaDeADN;
import mutantes.DetectorDeMutantes;
import excepciones.ExcepcionInputADN;
import excepciones.ExcepcionDeChequeo;
import baseDeDatos.OperacionesSobreBD;
import javax.ws.rs.core.Response;

/**
 *
 * @author Marco Iglesias
 */
@Stateless
@Path("entidades.dna")
public class DnaFacadeREST extends AbstractFacade<Dna> {

    @PersistenceContext(unitName = "DetectorDeMutantesPU")
    private EntityManager em;

    public DnaFacadeREST() {
        super(Dna.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Dna entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Dna entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Dna find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Dna> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Dna> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * Este servicio es el agregado que determina si un input pertenece o no a un mutante
     * Verifica que los datos ingresados correspondan a un humano o mutante, y que los 
     * datos tengan el formato correspondiente.
     * Si es mutante, devuelve un estado 200 OK, caso contrario, devuelve FORBIDDEN en el status
     * A su vez si los datos son validos, los guarda en la base de datos
     * @param entity
     * @return 
     */
    @POST
    @Path("mutant")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response esMutante(Dna entity) {
        boolean esMutante = false;
      
        try{
            //Armo la seciencia de ADN y me aseguro que sea valida (que contenga las letras indicadas
            // y tenga las medidas de N x N)
            SecuenciaDeADN secuenciaDeADN = new SecuenciaDeADN(entity.getDna());
            
            DetectorDeMutantes detectorDeMutantes = new DetectorDeMutantes(secuenciaDeADN);
            
            //Verifica si la matriz contiene secuencias repetidas para ver si es o no mutante
            //el adn
            if (detectorDeMutantes.esMutante()){
                esMutante = true;
            }
            
            //Conexion con la base de datos
            //En este punto, el ADN es valido y humano o mutante
            OperacionesSobreBD operacionesSobreBD = new OperacionesSobreBD();
            
            //Si es mutante, guardo el registro como mutante y sumo uno a la estadistica correspondiente
            //Si no es mutante, guardo el registro como humano y sumo uno a la estadistica correspondiente
            if (esMutante){
                operacionesSobreBD.sumarYAgregarADNMutante(secuenciaDeADN);
            }
            else{
                operacionesSobreBD.sumarYAgregarADNHumano(secuenciaDeADN);
            }
            
            //Cierro la conexion con la BD
            operacionesSobreBD.cerrarConexion();
        }
        catch(ExcepcionInputADN excepcionInputADN){
            //Guardar logs, manejar mensajes, etc
        }
        catch(ExcepcionDeChequeo excepcionDeChequeo){
            //Guardar logs, manejar mensajes, etc
        }
        catch(Exception e){
            //Guardar logs, manejar mensajes, etc
        }
        
        //Dependiendo del resultado del test, respondo con el status requerido
        if (esMutante){
            return Response.status(Response.Status.OK).build();
        }
        else{
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
    
}
