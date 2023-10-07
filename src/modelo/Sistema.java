package modelo;

import java.util.ArrayList;

public class Sistema {

    private static Sistema sistema;
    private ArrayList<Usuario> usuarios;
    private ArrayList<BaseDeDatos> basesDeDatos;

    public void setBasesDeDatos(ArrayList<BaseDeDatos> basesDeDatos) {
        this.basesDeDatos = basesDeDatos;
    }

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

    

    private Sistema() {
        this.usuarios = new ArrayList<Usuario>();
        this.basesDeDatos = new ArrayList <BaseDeDatos>();
    }

    public synchronized static Sistema getInstance() {
        if (sistema == null) {
            sistema = new Sistema();
        }
        return sistema;
    }
}
