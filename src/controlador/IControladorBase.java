
package controlador;

import java.util.ArrayList;
import modelo.BasedeDatos;
import modelo.Mensaje;
import modelo.Tabla;


public interface IControladorBase {
 
    public Mensaje CrearBase(int id, ArrayList<Tabla> tablas, String nombre);
    public BasedeDatos ObtenerBasedeDatosxid(int id);
    public Mensaje EliminarBase(int id);
    public Mensaje ModificarBasedeDatos(int id, ArrayList<Tabla> tablas, String nombre);
}
