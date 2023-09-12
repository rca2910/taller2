package controlador;

import modelo.Mensaje;
import modelo.Usuario;

public interface IControladorUsuario {

    public Mensaje AltaAdministrador(String cedula, String nombre, String apellido, String contrasena);

    public Mensaje AltaComun(String cedula, String nombre, String apellido, String contrasena);

    public Mensaje AltaLector(String cedula, String nombre, String apellido, String contrasena);

    public Usuario ObtenerUsuarioxCedula(String cedula);
}
