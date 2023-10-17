package controlador;

import java.util.ArrayList;
import modelo.Mensaje;
import modelo.Sistema;
import modelo.Usuario;
import modelo.eRolUsuario;
import modelo.eVersionUsuario;

public class ControladorUsuario implements IControladorUsuario {

    //Busca una cédula en la lista de usuarios y si la encuentra devuelve el usuario
    public Usuario ObtenerUsuarioxCedula(String cedula) {

        for (Usuario u : Sistema.getInstance().getUsuarios()) {
            if (cedula.equals(u.getCedula())) {

                return u;
            }
        }

        return null;
    }

    // Da de alta un usuario en la lista de usuarios
    public Mensaje altaUsuario(String cedula, String nombre, String apellido, String contrasena, eRolUsuario rol, eVersionUsuario versionUsuario) {

        Mensaje respuesta = new Mensaje("", false);
        if (ObtenerUsuarioxCedula(cedula) != null) {

            respuesta.setMensaje("El usuario ya se encuentra en el sistema");

            return respuesta;

        } else {
            Usuario nuevo = new Usuario(cedula, nombre, apellido, contrasena, rol, versionUsuario);

            Sistema.getInstance().getUsuarios().add(nuevo);

            respuesta.setMensaje("El usuario fue creado con exito");
            respuesta.setExito(true);

            return respuesta;
        }

    }

    // Da de baja un usuario de la lista de usuarios
    public Mensaje bajaUsuario(String cedula) {

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

    // Permite modificar datos del usuario
    public Mensaje modificarUsuario(String cedula, String nombre, String apellido, String contrasena, String nuevacedula, eRolUsuario rol, eVersionUsuario versionUsuario) {

        Mensaje respuesta = new Mensaje("", false);
        Usuario aBuscar = ObtenerUsuarioxCedula(cedula);

        if (aBuscar == null) {
            respuesta.setMensaje("El usuario ingresado no existe en el sistema");
            return respuesta;
        }
        if (!nuevacedula.equals(aBuscar.getCedula())) {
            Usuario usuarioYaRegistrado = ObtenerUsuarioxCedula(nuevacedula);
            if (usuarioYaRegistrado != null) {
                respuesta.setMensaje("La cedula ingresada ya fue tomada por otro usuario");
                return respuesta;
            }
        }
        aBuscar.setNombre(nombre);
        aBuscar.setApellido(apellido);
        aBuscar.setCedula(nuevacedula);
        aBuscar.setContrasena(contrasena);
        aBuscar.setRol(rol);
        aBuscar.setVersionUsuario(versionUsuario);

        respuesta.setMensaje("El usuario fue modificado con exito");
        respuesta.setExito(true);

        return respuesta;
    }

    // Busca una cédula y una contraseña, para saber si el usuario está registrado en la lista de usuarios
    
    public Usuario login(String cedula, String contrasena) {

        Usuario abuscar = this.ObtenerUsuarioxCedula(cedula);
        if (abuscar == null || !abuscar.getContrasena().equals(contrasena)) {

            return null;
        }
        return abuscar;
    }

    // Carga la lista de usuarios que está en el sistema
    public ArrayList<Usuario> obtenerUsuarios() {
        return Sistema.getInstance().getUsuarios();
    }

    // Controla que la cédula sea correcta
    private boolean ControlarCedula(String cedulaAControlar) {
        return false;
    }
}
