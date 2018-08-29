/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Pregunta;
import model.Respuesta;

/**
 *
 * @author Daniel Garro
 */
public class ServicioPregunta extends Servicio {

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

    public void agregarPregunta(Pregunta p) {

        try {
            this.conectar();
            //STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO tbl_pregunta (idUsuarioCarnet,idCategoria,contenido,fecha,tags,chatId)" + " VALUES (?,?,?,?,?,?)";
            paInsertar = conn.prepareStatement(sql);
            paInsertar.setInt(1, p.getIdUsuarioCarnet());
            paInsertar.setInt(2, p.getIdCategoria());
            paInsertar.setString(3, p.getContenido());
            paInsertar.setDate(4, p.getFecha());
            paInsertar.setString(5, p.getTags());
            paInsertar.setString(6, p.getChatId());

            paInsertar.executeUpdate();

        } catch (Exception ex) {
            System.out.println("No se pudo subir el documento..");
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
                System.out.println("\nNo pude cerrar...");
            }
        }
    }

    public List<Pregunta> obtenerListaPreguntas() {
        List<Pregunta> lst = new ArrayList<Pregunta>();
        ResultSet rs = null;
        try {
            this.conectar();
            //STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id,idUsuarioCarnet,idCategoria,contenido,fecha,tags,chatId FROM tbl_pregunta";
            rs = stmt.executeQuery(sql);

            //STEP 3.1: Extract data from result set
            while (rs.next()) {

                //Retrieve by column name
                int id = rs.getInt("id");
                int idUsuarioCarnet = rs.getInt("idUsuarioCarnet");
                int idCategoria = rs.getInt("idCategoria");
                String contenido = rs.getString("contenido");
                Date fecha = rs.getDate("fecha");
                String tags = rs.getString("tags");
                String chatId = rs.getString("chatId");
                List<Respuesta> lstRespuestas = new ServicioRespuesta().obtenerListaRespuestas(id);

                Pregunta p = new Pregunta(id, idUsuarioCarnet, idCategoria, contenido, fecha, tags,chatId, lstRespuestas);
                lst.add(p);
            }
        } catch (Exception ex) {
            System.out.println("No se pudo subir el documento..");
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
                System.out.println("\nNo pude cerrar...");
            }
        }

        return lst;
    }

}

