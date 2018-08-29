package model;

/**
 *
 * @author Daniel Garro
 */
import java.sql.Date;

public class Respuesta {

    private int idPregunta;
    private int idUsuarioCarnetRespuesta;
    private String contenido;
    private Date fecha;

    
    public Respuesta() {
    }

    public Respuesta(int idPregunta, int idUsuarioCarnetRespuesta, String contenido, Date fecha) {
        this.idPregunta = idPregunta;
        this.idUsuarioCarnetRespuesta = idUsuarioCarnetRespuesta;
        this.contenido = contenido;
        this.fecha = fecha;
   
    }

    public int getIdUsuarioCarnetRespuesta() {
        return idUsuarioCarnetRespuesta;
    }

    public void setIdUsuarioCarnetRespuesta(int idUsuarioCarnetRespuesta) {
        this.idUsuarioCarnetRespuesta = idUsuarioCarnetRespuesta;
    }

   


    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getIdUsuarioRespuesta() {
        return idUsuarioCarnetRespuesta;
    }

    public void setIdUsuarioRespuesta(int idUsuarioCarnetRespuesta) {
        this.idUsuarioCarnetRespuesta = idUsuarioCarnetRespuesta;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Respuesta{" + "idPregunta=" + idPregunta + ", idUsuarioCarnetRespuesta=" + idUsuarioCarnetRespuesta + ", contenido=" + contenido + ", fecha=" + fecha + '}';
    }

}
