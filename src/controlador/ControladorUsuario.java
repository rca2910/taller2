package controlador;

import modelo.Administrador;
import modelo.Comun;
import modelo.Lector;
import modelo.Mensaje;
import modelo.Sistema;
import modelo.Usuario;

public class ControladorUsuario implements IControladorUsuario {

    public Usuario ObtenerUsuarioxCedula(String cedula) {

        for (Administrador a : Sistema.getInstance().getAdministradores()) {
            if (cedula.equals(a.getCedula())) {

                return a;
            }
        }
        for (Comun c : Sistema.getInstance().getComunes()) {
            if (cedula.equals(c.getCedula())) {

                return c;
            }

        }
        for (Lector l : Sistema.getInstance().getLectores()) {
            if (cedula.equals(l.getCedula())) {

                return l;
            }

        }
        return null;
    }

    public Mensaje AltaAdministrador(String cedula, String nombre, String apellido, String contrasena) {

        Mensaje respuesta = new Mensaje("", false);
        if (ObtenerUsuarioxCedula(cedula) != null) {

            respuesta.setMensaje("El usuario ya se encuentra en el sistema");

            return respuesta;

        } else {
            Administrador nuevo = new Administrador(cedula, nombre, apellido, contrasena);

            Sistema.getInstance().getAdministradores().add(nuevo);

            respuesta.setMensaje("El usuario fue creado con exito");
            respuesta.setExito(true);

            return respuesta;
        }

    }

    public Mensaje AltaComun(String cedula, String nombre, String apellido, String contrasena) {

        Mensaje respuesta = new Mensaje("", false);
        if (ObtenerUsuarioxCedula(cedula) != null) {

            respuesta.setMensaje("El usuario ya se encuentra en el sistema");

            return respuesta;

        } else {
            Comun nuevo = new Comun(cedula, nombre, apellido, contrasena);

            Sistema.getInstance().getComunes().add(nuevo);

            respuesta.setMensaje("El usuario fue creado con exito");
            respuesta.setExito(true);

            return respuesta;
        }
    }

    public Mensaje AltaLector(String cedula, String nombre, String apellido, String contrasena) {

        Mensaje respuesta = new Mensaje("", false);
        if (ObtenerUsuarioxCedula(cedula) != null) {

            respuesta.setMensaje("El usuario ya se encuentra en el sistema");

            return respuesta;

        } else {
            Lector nuevo = new Lector(cedula, nombre, apellido, contrasena);

            Sistema.getInstance().getLectores().add(nuevo);

            respuesta.setMensaje("El usuario fue creado con exito");
            respuesta.setExito(true);

            return respuesta;
        }
    }

    public Mensaje BajaAdministrador(String cedula) {

        Usuario aBuscar = ObtenerUsuarioxCedula(cedula);
        Mensaje respuesta = new Mensaje("", false);
        if ((aBuscar) != null) {

            Sistema.getInstance().getAdministradores().remove(aBuscar);
            Sistema.getInstance().getLectores().remove(aBuscar);
            Sistema.getInstance().getComunes().remove(aBuscar);

            respuesta.setMensaje("El usuario ha sido eliminado");
            respuesta.setExito(true);
            return respuesta;
        }
        else {
            
            respuesta.setMensaje ("El usuario no existe en el sistema");
            return respuesta;
            
    }

}
}
