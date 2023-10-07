package controlador;

import java.util.ArrayList;
import modelo.BaseDeDatos;
import modelo.Mensaje;
import modelo.Sistema;

public class ControladorBase implements IControladorBase {
    
    public Mensaje altaBase(String nombre) 
    {
        Mensaje respuesta = new Mensaje("", false);
        
        boolean nombreOcupado = nombreBaseOcupado(nombre);
        
        if(nombreOcupado)
        {
            respuesta.setMensaje("Ya existe una base de datos con el nombre ingresado");
        }
        else
        {
            BaseDeDatos nueva = new BaseDeDatos(nombre);
            Sistema.getInstance().getBasesDeDatos().add(nueva);
            respuesta.setMensaje("La base de datos fue creada exitosamente");
            respuesta.setExito(true);
        }

        return respuesta;
    }

    public Mensaje bajaBase(int id) 
    {
        BaseDeDatos aBuscar = obtenerBaseXId (id);
        Mensaje respuesta = new Mensaje("", false);
        
        if ((aBuscar) != null) 
        {
            Sistema.getInstance().getBasesDeDatos().remove(aBuscar);

            respuesta.setMensaje("La base de datos fue eliminada exitosamente");
            respuesta.setExito(true);
            return respuesta;
        } 
        else 
        {
            respuesta.setMensaje("La base de datos no existe en el sistema");
            return respuesta;
        }

    }
    
    public Mensaje renombrarBase(int id, String nombre)
    {
        Mensaje respuesta = new Mensaje("", false);
        BaseDeDatos aBuscar = obtenerBaseXId(id);

        if (aBuscar == null) 
        {
            respuesta.setMensaje("La base de datos no existe en el sistema");
            return respuesta;
        }
        
        boolean nombreOcupado = nombreBaseOcupado(nombre);
        
        if(nombreOcupado)
        {
            respuesta.setMensaje("El nombre ya esta siendo usado por otra base de datos");
            return respuesta;
        }
        
        aBuscar.setNombre(nombre);
        respuesta.setMensaje("La base de datos fue modificada exitosamente");
        respuesta.setExito(true);

        return respuesta;
    }
    
    public BaseDeDatos obtenerBaseXId(int id) {

        for (BaseDeDatos b : Sistema.getInstance().getBasesDeDatos()) {
            if (id==(b.getId())) {

                return b;
            }
        }

        return null;
    }
    
    public boolean nombreBaseOcupado(String nombre)
    {
        for (BaseDeDatos b : Sistema.getInstance().getBasesDeDatos())
        {
            if(nombre.toUpperCase().equals(b.getNombre()))
            {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<BaseDeDatos> obtenerBases()
    {
        return Sistema.getInstance().getBasesDeDatos();
    }
}
