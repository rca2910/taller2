package modelo;

import java.util.ArrayList;


public class Tabla {

    private String nombre;
    private ArrayList<Columna> columnas;
    
    
    // Constructores 
    
    public Tabla(String nombre) {
        this.nombre = nombre;
        this.columnas = new ArrayList<Columna>();
    }
    
    public Tabla(String nombre, ArrayList<Columna> columna) {
        this.nombre = nombre;
        this.columnas = columna;
    }

    // Getters y Setters 
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Columna> getColumnas() {
        return columnas;
    }

    public void setColumnas(ArrayList<Columna> columnas) {
        this.columnas = columnas;
    }
    
    // Busca una columna en la tabla
    
    public Columna getColumnaXNombre(String nombreColumna)
    {
        for(Columna aBuscar : this.getColumnas())
        {
            if(aBuscar.getNombre().equals(nombreColumna))
            {
                return aBuscar;
            }
        }
        return null;
    }
}
