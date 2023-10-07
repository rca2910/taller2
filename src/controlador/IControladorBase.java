package controlador;

import java.util.ArrayList;
import modelo.BaseDeDatos;
import modelo.Mensaje;


public interface IControladorBase {
    public Mensaje altaBase(String nombre);
    public Mensaje bajaBase(int id);
    public Mensaje renombrarBase(int id, String nombre);
    public BaseDeDatos obtenerBaseXId(int id);
    public boolean nombreBaseOcupado(String nombre);
    public ArrayList<BaseDeDatos> obtenerBases();
}
