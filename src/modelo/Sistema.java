package modelo;

import java.util.ArrayList;

public class Sistema {

    private static Sistema sistema;
    private ArrayList<Usuario> usuarios;
    private ArrayList<BaseDeDatos> basesDeDatos;
    

    // Constructor
    
    public void setBasesDeDatos(ArrayList<BaseDeDatos> basesDeDatos) {
        this.basesDeDatos = basesDeDatos;
    }

    // Getters y Setters
    
    public ArrayList<BaseDeDatos> getBasesDeDatos() {
        return basesDeDatos;
    }
    

    public static Sistema getSistema() {
        return sistema;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    

    public static void setSistema(Sistema sistema) {
        Sistema.sistema = sistema;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    

    // Constructor para la carga de bases de datos y usuarios al inicio
    
    private Sistema() {
        this.usuarios = new ArrayList<Usuario>();
        this.basesDeDatos = new ArrayList <BaseDeDatos>();
    }

    // Método que permite que se cree una única instancia del sistema.
    
    public synchronized static Sistema getInstance() {
        if (sistema == null) {
            sistema = new Sistema();
        }
        return sistema;
    }
}
