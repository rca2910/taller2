
package modelo;

import java.util.ArrayList;


public class BasedeDatos {
    private int id;
    private ArrayList<Tabla> tablas;
    private String nombre;
    
    public BasedeDatos(int id, ArrayList<Tabla> tablas, String nombre) {
        this.id = id;
        this.tablas = tablas;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
