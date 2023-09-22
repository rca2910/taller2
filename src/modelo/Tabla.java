
package modelo;

import java.util.ArrayList;
import java.util.List;


public class Tabla {
    
    private int id;
    private String nombre;
    private ArrayList<Columna> columna;
    
    public Tabla(int id, String nombre, ArrayList<Columna> columna) {
        this.id = id;
        this.nombre = nombre;
        this.columna = columna;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Columna> getColumna() {
        return columna;
    }

    public void setColumna(ArrayList<Columna> columna) {
        this.columna = columna;
    }
}
