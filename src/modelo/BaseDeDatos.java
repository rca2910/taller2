package modelo;

import java.util.ArrayList;


public class BaseDeDatos {
    private int id;
    private ArrayList<Tabla> tablas;
    private String nombre;
    
    private static int contador = 0;
    
    public BaseDeDatos(String nombre) {
        this.id = ++contador;
        this.tablas = new ArrayList<Tabla>();
        this.nombre = nombre.toUpperCase();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Tabla> getTablas() {
        return tablas;
    }

    public void setTablas(ArrayList<Tabla> tablas) {
        this.tablas = tablas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Id: " + id + " " + "Nombre: " + nombre;
    }
    
}
