
package modelo;

import java.util.ArrayList;
import java.util.List;


public class Columna {
    
    private String nombre;
    private ArrayList celdas;
    private eTipoColumna tipo;
    private boolean nulleable;

    // Constructores
    
    public Columna(String nombre, ArrayList celda, eTipoColumna estado, boolean nulleable) {
        this.nombre = nombre = nombre.toUpperCase();
        this.celdas = celda;
        this.tipo = estado;
        this.nulleable = nulleable;
    }
    
    public Columna(String nombre, eTipoColumna estado, boolean nulleable) {
        this.nombre = nombre.toUpperCase();
        this.celdas = new ArrayList<Celda>();
        this.tipo = estado;
        this.nulleable = nulleable;
    }

    //Getters y Setters 
    
    public String getNombre() {
        return nombre;
    }

    public List getCeldas() {
        return celdas;
    }

    public eTipoColumna getTipo() {
        return tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public void setCeldas(ArrayList celdas) {
        this.celdas = celdas;
    }

    public void setTipo(eTipoColumna tipo) {
        this.tipo = tipo;
    }
    
     public boolean isNulleable() {
        return nulleable;
    }

    public void setNulleable(boolean nulleable) {
        this.nulleable = nulleable;
    }
    
}
