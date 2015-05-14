package skynet;

/*
 * Clase que representa un comentario que
 * se otorga a un comercio de Skynet
 * @author Mijail Gutiérrez Valdez
 * @version 1.0
 */
public class Comentario {
    /*
     * comentario otorgada
     */
    public String comentario;

    /*
     * fecha cuando se hizo el comentario
     */
    public Date fecha;


    /*
     * Metodo getter de comentario
     */
    public String getComentario() {
        return this.comentario;
    }

    /*
     * Metodo getter de fecha
     */
    public Date getFecha() {
        return this.fecha;
    }

    /*
     * Metodo setter de fecha
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /*
     * Metodo setter de comentario
     * @param comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
    * Mapeo en la base de datos
    */
    public static mapping = {
        comentario sqlType: 'longblob';
    }

    /*
     * Restricciones del sistema en la base
     * de datos, closure de groovy.
     */
    static belongsTo = [usuario:Usuario, comercio:Comercio];
}