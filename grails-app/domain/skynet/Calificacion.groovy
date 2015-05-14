package skynet;

/*
 * Clase que representa una calificacion
 * que se otroca a un comercio de Skynet
 * @author Mijail Gutiérrez Valdez
 * @version 1.0
 */
public class Calificacion {

    /*
     * calificación otorgada
     */
    public int calificacion;

    /*
     * Metodo getter de calificación
     */
    public int getCalificacion() {
        return this.calificacion;
    }
 
    /*
     * Metodo setter de calificación
     * @param calificacion
     */
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    /*
     * Restricciones del sistema en la base
     * de datos, closure de groovy.
     */
    static belongsTo = [usuario:Usuario, comercio:Comercio];
}
