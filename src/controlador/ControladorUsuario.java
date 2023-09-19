package controlador;

import modelo.Mensaje;
import modelo.Sistema;
import modelo.Usuario;
import modelo.eRolUsuario;

public class ControladorUsuario implements IControladorUsuario {

    public Usuario ObtenerUsuarioxCedula(String cedula) {

        for (Usuario u : Sistema.getInstance().getUsuarios()) {
            if (cedula.equals(u.getCedula())) {

                return u;
            }
        }

        return null;
    }

    public Mensaje AltaUsuario(String cedula, String nombre, String apellido, String contrasena, eRolUsuario rol) {

        Mensaje respuesta = new Mensaje("", false);
        if (ObtenerUsuarioxCedula(cedula) != null) {

            respuesta.setMensaje("El usuario ya se encuentra en el sistema");

            return respuesta;

        } else {
            Usuario nuevo = new Usuario(cedula, nombre, apellido, contrasena, rol);

            Sistema.getInstance().getUsuarios().add(nuevo);

            respuesta.setMensaje("El usuario fue creado con exito");
            respuesta.setExito(true);

            return respuesta;
        }

    }

    public Mensaje BajaUsuario(String cedula) {

        Usuario aBuscar = ObtenerUsuarioxCedula(cedula);
        Mensaje respuesta = new Mensaje("", false);
        if ((aBuscar) != null) {

            Sistema.getInstance().getUsuarios().remove(aBuscar);

            respuesta.setMensaje("El usuario ha sido eliminado");
            respuesta.setExito(true);
            return respuesta;
        } else {

            respuesta.setMensaje("El usuario no existe en el sistema");
            return respuesta;

        }

    }

    public Mensaje ModificarUsuario(String cedula, String nombre, String apellido, String contrasena, String nuevacedula, eRolUsuario rol) {

        Mensaje respuesta = new Mensaje("", false);
        Usuario aBuscar = ObtenerUsuarioxCedula(cedula);

        if (aBuscar == null) {

            respuesta.setMensaje("El usuario ingresado no existe en el sistema");

            return respuesta;

        } else {

            aBuscar.setNombre(nombre);
            aBuscar.setApellido(apellido);
            aBuscar.setCedula(nuevacedula);
            aBuscar.setContrasena(contrasena);
            aBuscar.setRol(rol);

            respuesta.setMensaje("El usuario fue modificado con exito");
            respuesta.setExito(true);

            return respuesta;
        }
    }

    public Usuario Login(String cedula, String contrasena) {

        Usuario abuscar = this.ObtenerUsuarioxCedula(cedula);
        if (abuscar == null || !abuscar.getContrasena().equals(contrasena)) {

            return null;
        }
        return abuscar;
    }

}
