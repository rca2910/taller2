
package modelo;

import java.util.ArrayList;
import java.util.List;


public class Columna {
    
    private String nombre;
    private ArrayList celdas;
    private eTipoColumna tipo;
    private boolean nulleable;

    public Columna(String nombre, ArrayList celda, eTipoColumna estado, boolean nulleable) {
        this.nombre = nombre;
        this.celdas = celda;
        this.tipo = estado;
        this.nulleable = nulleable;
    }
    
    public Columna(String nombre, eTipoColumna estado, boolean nulleable) {
        this.nombre = nombre;
        this.celdas = new ArrayList<Celda>();
        this.tipo = estado;
        this.nulleable = nulleable;
    }

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
        this.nombre = nombre;
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
