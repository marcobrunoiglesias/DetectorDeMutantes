/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entidades.Estadistica;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
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

/**
 *
 * @author Marco Iglesias
 */
@Stateless
@Path("entidades.estadistica")
public class EstadisticaFacadeREST extends AbstractFacade<Estadistica> {

    @PersistenceContext(unitName = "DetectorDeMutantesPU")
    private EntityManager em;

    public EstadisticaFacadeREST() {
        super(Estadistica.class);
        this.em = Persistence.createEntityManagerFactory("DetectorDeMutantesPU").createEntityManager();
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Estadistica entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Estadistica entity) {
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
    public Estadistica find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Estadistica> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Estadistica> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
     * Servicio agregado para recuperar las estadisticas
     * @return 
     */
    @GET
    @Path("stats")
    @Produces({MediaType.APPLICATION_JSON})
    public String stats() {
        //Existe solo un registro como estadistica, lo recupero para armar el JSON de salida
        //Se guarda la estadistica aparte para que los requerimentos sean mas agiles en caso de 
        //tener una base de datos de grandes dimensiones.
        //Tambien se podria contar directamente la cantidad de mutantes y humanos desde la base de datos.
        Estadistica estadistica = super.find(1);
        int mutantes = estadistica.getCountMutantDna();
        int humanos = estadistica.getCountHumanDna();
        double ratio = 0;
        if ((humanos)>0){  
            ratio = (double) mutantes /humanos;
        }
        return ("{\"count_mutant_dna\":"+mutantes+", \"count_human_dna\":"+humanos+", \"ratio\":"+ratio+"}");
    }
    
}
