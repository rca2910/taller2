
package modelo;

import java.util.List;


public class Columna {
    
    private int id;
    private String nombre;
    private List fila;
    private EstadoColumna estado;

    public Columna(int id, String nombre, List fila, EstadoColumna estado) {
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

    public void setFila(List fila) {
        this.fila = fila;
    }

    public void setEstado(EstadoColumna estado) {
        this.estado = estado;
    }
    
    
    
}
