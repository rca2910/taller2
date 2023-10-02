package modelo;

import java.util.ArrayList;

public class Sistema {

    private static Sistema sistema;
    private ArrayList<Usuario> usuarios;
    private ArrayList<BasedeDatos> basededatos;

    public void setBasededatos(ArrayList<BasedeDatos> basededatos) {
        this.basededatos = basededatos;
    }

    public ArrayList<BasedeDatos> getBasededatos() {
        return basededatos;
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
        this.basededatos = new ArrayList <BasedeDatos>();
    }

    public synchronized static Sistema getInstance() {
        if (sistema == null) {
            sistema = new Sistema();
        }
        return sistema;
    }
}
