
package modelo;

import java.util.ArrayList;


public class BasedeDatos {
    private int id;
    private ArrayList<Tabla> tablas;
    private String nombre;
    
    private static int contador =0;
    
    public BasedeDatos(int id, ArrayList<Tabla> tablas, String nombre) {
        this.id = ++ contador;
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

    @Override
    public String toString() {
        return "BasedeDatos{" + "id=" + id + ", tablas=" + tablas + ", nombre=" + nombre + '}';
    }
    
}
