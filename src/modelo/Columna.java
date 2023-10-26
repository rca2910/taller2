
package modelo;

import java.io.Serializable;
import java.util.ArrayList;


public class Columna implements Serializable {
    
    private String nombre;
    private ArrayList<Celda> celdas;
    private eTipoColumna tipo;
    private boolean nulleable;

    // Constructores
    
    public Columna(String nombre, ArrayList<Celda> celda, eTipoColumna tipo, boolean nulleable) {
        this.nombre = nombre = nombre.toUpperCase();
        this.celdas = celda;
        this.tipo = tipo;
        this.nulleable = nulleable;
    }
    
    public Columna(String nombre, eTipoColumna tipo, boolean nulleable) {
        this.nombre = nombre.toUpperCase();
        this.celdas = new ArrayList<Celda>();
        this.tipo = tipo;
        this.nulleable = nulleable;
    }

    //Getters y Setters 
    
    public String getNombre() {
        return nombre;
    }

    public ArrayList<Celda> getCeldas() {
        return celdas;
    }

    public eTipoColumna getTipo() {
        return tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public void setCeldas(ArrayList<Celda> celdas) {
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
