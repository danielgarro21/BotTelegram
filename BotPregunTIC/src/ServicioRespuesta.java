/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Respuesta;

/**
 *
 * @author Daniel Garro
 */
public class ServicioRespuesta extends Servicio {

    @Override
    public void conectar() {
        try {
            //STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException ex) {
            System.out.println("No se puedo registrar el driver...");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("No se puedo conectar...");
        }
    }

    public List<Respuesta> obtenerListaRespuestas(int idPregunta) {
        
        List<Respuesta> lst = new ArrayList<>();
        try {
            this.conectar();
            //STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT idPregunta,idUsuarioCarnetRespuesta,contenido,fecha FROM tbl_respuesta WHERE idPregunta=(?)";
            paInsertar = conn.prepareStatement(sql);
            paInsertar.setInt(1, idPregunta);
            paInsertar.execute();
            //STEP 3.1: Extract data from result set
            while (paInsertar.getResultSet().next()) {
               
                //Retrieve by column name
                int idPregunta1 = paInsertar.getResultSet().getInt("idPregunta");
                int idUsuarioCarnetRespuesta = paInsertar.getResultSet().getInt("idUsuarioCarnetRespuesta");
                String contenido = paInsertar.getResultSet().getString("contenido");
                Date fecha = paInsertar.getResultSet().getDate("fecha");
                
                Respuesta r = new Respuesta( idPregunta1, idUsuarioCarnetRespuesta, contenido, fecha);
                lst.add(r);
                
                
                }
        } catch (Exception ex) {
            System.out.println("No se puedo realizar la consulta de Archivos...");
        } finally {
            try {
                if (!paInsertar.isClosed()) {
                    paInsertar.close();
                }
                if (!stmt.isClosed()) {
                    stmt.close();
                }
                if (!conn.isClosed()) {
                    conn.close();
                }

            } catch (Exception e) {
                System.out.println("No pude cerrar...");
            }
        }
        return lst;
    }
}