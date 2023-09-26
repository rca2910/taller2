package controlador;

import java.util.ArrayList;
import modelo.Mensaje;
import modelo.Usuario;
import modelo.eRolUsuario;
import modelo.eVersionUsuario;

public interface IControladorUsuario {

    public Mensaje AltaUsuario(String cedula, String nombre, String apellido, String contrasena, eRolUsuario rol, eVersionUsuario versionUsuario);

    public Mensaje BajaUsuario(String cedula);

    public Mensaje ModificarUsuario(String cedula, String nombre, String apellido, String contrasena, String nuevacedula, eRolUsuario rol, eVersionUsuario versionUsuario);

    public Usuario Login(String cedula, String contrasena);
    public ArrayList<Usuario> ObtenerUsuarios();
}
