
package modelo;

import java.util.ArrayList;
import java.util.List;


public class Columna {
    
    private int id;
    private String nombre;
    private ArrayList fila;
    private EstadoColumna estado;

    public Columna(int id, String nombre, ArrayList fila, EstadoColumna estado) {
        this.id = id;
        this.nombre = nombre;
        this.fila = fila;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List getFila() {
        return fila;
    }

    public EstadoColumna getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFila(ArrayList fila) {
        this.fila = fila;
    }

    public void setEstado(EstadoColumna estado) {
        this.estado = estado;
    }
    
    
    
}
