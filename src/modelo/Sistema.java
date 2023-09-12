package modelo;

import java.util.ArrayList;

public class Sistema {

    private static Sistema sistema;
    private ArrayList<Administrador> administradores;
    private ArrayList<Comun> comunes;
    private ArrayList<Lector> lectores;

    public static Sistema getSistema() {
        return sistema;
    }

    public ArrayList<Administrador> getAdministradores() {
        return administradores;
    }

    public ArrayList<Comun> getComunes() {
        return comunes;
    }

    public ArrayList<Lector> getLectores() {
        return lectores;
    }

    public static void setSistema(Sistema sistema) {
        Sistema.sistema = sistema;
    }

    public void setAdministradores(ArrayList<Administrador> administradores) {
        this.administradores = administradores;
    }

    public void setComunes(ArrayList<Comun> comunes) {
        this.comunes = comunes;
    }

    public void setLectores(ArrayList<Lector> lectores) {
        this.lectores = lectores;
    }

    private Sistema() {
        this.administradores = new ArrayList<Administrador>();
        this.comunes = new ArrayList<Comun>();
        this.lectores = new ArrayList<Lector>();
    }

    public synchronized static Sistema getInstance() {
        if (sistema == null) {
            sistema = new Sistema();
        }
        return sistema;
    }
}
