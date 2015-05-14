package skynet;

/*
 * Clase que representa a un estacion cerca
 * de un comercio de Skynet
 * @author Mijail Gutiérrez Valdez
 * @version 1.0
 */
public class Estacion {
    /*
     * Nombre de la estacion
     */
    public String nombre;

    /*
     * Metodo getter de nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /*
     * Metodo setter de nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*
     * Restricciones del sistema en la base
     * de datos, closure de groovy.
     */
    public static constraints = {
        nombre size: 1..100, blank: false;
        
    }

    /*
     * Restricciones del sistema en la base
     * de datos, closure de groovy.
     */
    static belongsTo = [transporte:Transporte];
}