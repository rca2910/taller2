package controlador;

import java.util.ArrayList;
import modelo.Mensaje;
import modelo.Usuario;
import modelo.eRolUsuario;
import modelo.eVersionUsuario;

public interface IControladorUsuario {

    public Mensaje altaUsuario(String cedula, String nombre, String apellido, String contrasena, eRolUsuario rol, eVersionUsuario versionUsuario);

    public Mensaje bajaUsuario(String cedula);

    public Mensaje modificarUsuario(String cedula, String nombre, String apellido, String contrasena, String nuevacedula, eRolUsuario rol, eVersionUsuario versionUsuario);

    public Usuario login(String cedula, String contrasena);
    
    public ArrayList<Usuario> obtenerUsuarios();
}
