package controlador;

import java.util.ArrayList;
import modelo.BaseDeDatos;
import modelo.Columna;
import modelo.Mensaje;
import modelo.MensajeQuery;
import modelo.Tabla;
import modelo.eRolUsuario;
import modelo.eVersionUsuario;


public interface IControladorBase {
    public Mensaje altaBase(String nombre);
    public Mensaje bajaBase(int id);
    public Mensaje renombrarBase(int id, String nombre);
    public BaseDeDatos obtenerBaseXId(int id);
    public boolean nombreBaseOcupado(String nombre);
    public ArrayList<BaseDeDatos> obtenerBases();
    public Mensaje agregarTabla(BaseDeDatos base, Tabla tabla);
    public Mensaje agregarVariasColumnas(Tabla tabla, ArrayList<Columna> columnas);
    public Mensaje agregarColumna(Tabla tabla, Columna columna);
    public MensajeQuery ejecutarQuery(int idBaseSeleccionada, String query, eVersionUsuario versionUsuario, eRolUsuario rolUsuario);
}
