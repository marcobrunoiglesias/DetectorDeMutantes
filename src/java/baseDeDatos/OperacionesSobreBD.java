/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDeDatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import mutantes.SecuenciaDeADN;

/**
 *
 * @author Marco Iglesias
 */
public class OperacionesSobreBD {
    private final static String nombreBD = "detector_mutantes_bd";
    private final static String usuario = "mercadolibre";
    private final static String pass = "MercadoLibre_2018";
    private final static String url = "jdbc:mysql://localhost/"+nombreBD;
    private Connection conn;

    /**
     * Genera una conexion a la base de datos de mutantes
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws Exception 
     */
    public OperacionesSobreBD() throws SQLException,ClassNotFoundException,Exception{

        try{
            Class.forName("com.mysql.jdbc.Driver");         
            conn = DriverManager.getConnection(url,usuario,pass);
            if (conn==null){
               throw new Exception("No se pudo conectar con la base de datos");
            }
        }
        catch(SQLException sQLException){
            throw sQLException;
        }
        catch(ClassNotFoundException classNotFoundException){
            throw classNotFoundException;
        }
        catch(Exception e){
            throw e;
        }
    }
    

    /**
     * Inserta una secuencia valida de ADN mutante. No verifica si la matriz de dimensiones N x N.
     * Tampoco verifica que la secuencia sea valida en sus parametros.
     * @param secuenciaDeADN
     * @throws Exception 
     */
    public void sumarYAgregarADNMutante(SecuenciaDeADN secuenciaDeADN) throws Exception{
    
        try{
            String consulta = "insert into secuencias_adn_procesadas values (0,1,'"+secuenciaDeADN.getSecuenciasConcatenadas()+"')";
            
            //Las dos operaciones se tienen que ejecutar para mantener la consistencia de la BD
            this.conn.setAutoCommit(false);
            
            //Inserto la secuencia de ADN
            Statement statement = this.conn.createStatement();
            statement.executeUpdate(consulta);
            
            //Sumo un valor a los mutantes//Inserto la secuencia de ADN
            consulta = "update estadistica set count_mutant_dna=count_mutant_dna+1 where id=1";
            statement.executeUpdate(consulta);
            
            this.conn.commit();
        
        }
        catch(Exception excepcionAlActualizarDatos){
            try{
                if (this.conn !=null){
                    this.conn.rollback();
                }
            }
            catch(Exception excepcionAlHacerRollBack){
                throw excepcionAlHacerRollBack;
            }
            throw excepcionAlActualizarDatos;
        }
        finally{
            //Vuelvo a setear el autocommit en true por si otra operacion se ejecuta con la misma conexion
            if (this.conn != null){
                this.conn.setAutoCommit(true);
            }
        }
    }
    
    /**
     * Inserta una secuencia valida de ADN humano. No verifica si la matriz de dimensiones N x N.
     * Tampoco verifica que la secuencia sea valida en sus parametros.
     * @param secuenciaDeADN
     * @throws Exception 
     */
    public void sumarYAgregarADNHumano(SecuenciaDeADN secuenciaDeADN)throws Exception{
        try{
            String consulta = "insert into secuencias_adn_procesadas values (0,0,'"+secuenciaDeADN.getSecuenciasConcatenadas()+"')";
            
            //Las dos operaciones se tienen que ejecutar para mantener la consistencia de la BD
            this.conn.setAutoCommit(false);
            
            //Inserto la secuencia de ADN
            Statement statement = this.conn.createStatement();
            statement.executeUpdate(consulta);
            
            //Sumo un valor a los Humanos
            consulta = "update estadistica set count_human_dna=count_human_dna+1 where id=1";
            statement.executeUpdate(consulta);
            
            this.conn.commit();
        
        }
        catch(Exception excepcionAlActualizarDatos){
            try{
                if (this.conn !=null){
                    this.conn.rollback();
                }
            }
            catch(Exception excepcionAlHacerRollBack){
                throw excepcionAlHacerRollBack;
            }
            throw excepcionAlActualizarDatos;
        }
        finally{
            //Vuelvo a setear el autocommit en true por si otra operacion se ejecuta con la misma conexion
            if (this.conn != null){
                this.conn.setAutoCommit(true);
            }
        }
    }
    
    /**
     * Cierra la conexion con la base de datos de mutantes
     * @throws Exception 
     */
    public void cerrarConexion() throws Exception{
        if (this.conn!=null){
            this.conn.close();
        }
        
    }
    
}
