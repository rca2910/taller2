package controlador;

import java.util.ArrayList;
import modelo.BasedeDatos;
import modelo.Mensaje;
import modelo.Sistema;
import modelo.Tabla;

public class ControladorBase implements IControladorBase {

    public BasedeDatos ObtenerBasedeDatosxid(int id) {

        for (BasedeDatos b : Sistema.getInstance().getBasededatos()) {
            if (id==(b.getId())) {

                return b;
            }
        }

        return null;
    }
    public Mensaje CrearBase(int id, ArrayList<Tabla> tablas, String nombre) {

        Mensaje respuesta = new Mensaje("", false);
        BasedeDatos nuevo = new BasedeDatos(id, tablas, nombre);

        Sistema.getInstance().getBasededatos().add(nuevo);

        respuesta.setMensaje("La base de datos fue creado con exito");
        respuesta.setExito(true);

        return respuesta;
    }

    public Mensaje EliminarBase(int id) {

        BasedeDatos aBuscar = ObtenerBasedeDatosxid (id);
        Mensaje respuesta = new Mensaje("", false);
        if ((aBuscar) != null) {

            Sistema.getInstance().getBasededatos().remove(aBuscar);

            respuesta.setMensaje("La base de datos ha sido eliminada del sistema");
            respuesta.setExito(true);
            return respuesta;
        } else {

            respuesta.setMensaje("La base de datos no existe en el sistema");
            return respuesta;

        }

    }
    
    public Mensaje ModificarBasedeDatos(int id, ArrayList<Tabla> tablas, String nombre){
        Mensaje respuesta = new Mensaje("", false);
        BasedeDatos aBuscar = ObtenerBasedeDatosxid(id);

        if (aBuscar == null) {
            respuesta.setMensaje("La base de datos no existe en el sistema");
            return respuesta;
        }
        
        
        
        aBuscar.setNombre(nombre);
        
        respuesta.setMensaje("La base de datos fue modificada con exito");
        respuesta.setExito(true);

        return respuesta;
    }
}
