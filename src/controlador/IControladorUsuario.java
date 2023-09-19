package controlador;

import modelo.Mensaje;
import modelo.Usuario;
import modelo.eRolUsuario;

public interface IControladorUsuario {

    public Mensaje AltaUsuario(String cedula, String nombre, String apellido, String contrasena, eRolUsuario rol);

    public Mensaje BajaUsuario(String cedula);

    public Mensaje ModificarUsuario(String cedula, String nombre, String apellido, String contrasena, String nuevacedula, eRolUsuario rol);

    public Usuario Login(String cedula, String contrasena);
}
