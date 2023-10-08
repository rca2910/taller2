
package modelo;

import java.util.ArrayList;
import java.util.List;


public class Columna {
    
    private int id;
    private String nombre;
    private ArrayList celda;
    private eEstadoColumna estado;

    public Columna(int id, String nombre, ArrayList celda, eEstadoColumna estado) {
        this.id = id;
        this.nombre = nombre;
        this.celda = celda;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List getCelda() {
        return celda;
    }

    public eEstadoColumna getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCelda(ArrayList celda) {
        this.celda = celda;
    }

    public void setEstado(eEstadoColumna estado) {
        this.estado = estado;
    }
    
    
    
}
