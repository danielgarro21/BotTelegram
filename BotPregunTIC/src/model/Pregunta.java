
package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Daniel Garro
 */

public class Pregunta {
    private int id;
    private int idUsuarioCarnet;
    private int idCategoria;
    private String contenido;
    private Date fecha;
    private String tags;
    private String chatId;
    private List<Respuesta> lstRespuestasPregunta=new ArrayList<>();

    public Pregunta() {
    }


    public Pregunta(int id, int idUsuarioCarnet, int idCategoria, String contenido, Date fecha, String tags,String chatId,List<Respuesta> lstRespuestaPregunta) {
        this.id = id;
        this.idUsuarioCarnet = idUsuarioCarnet;
        this.idCategoria = idCategoria;
        this.contenido = contenido;
        this.fecha = fecha;
        this.tags = tags;
        this.chatId = chatId;
        this.lstRespuestasPregunta = lstRespuestaPregunta;
        
    }

    public int getIdUsuarioCarnet() {
        return idUsuarioCarnet;
    }

    public void setIdUsuarioCarnet(int idUsuarioCarnet) {
        this.idUsuarioCarnet = idUsuarioCarnet;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChatId(){
        return chatId;
    }
    public void setChatId(String chatId){
        this.chatId = chatId;
    }
    public List<Respuesta> getLstRespuestasPregunta() {
        return lstRespuestasPregunta;
    }

    public void setLstRespuestasPregunta(List<Respuesta> lstRespuestasPregunta) {
        this.lstRespuestasPregunta = lstRespuestasPregunta;
    }
    
    
    

    @Override
    public String toString() {
        return "Pregunta{" + ", idUsuarioCarnet=" + idUsuarioCarnet + ", idCategoria=" + idCategoria + ", contenido=" + contenido + ", fecha=" + fecha + ", tags=" + tags + '}';
    }
    
    
}
