package controlador;

import modelo.Mensaje;
import modelo.Usuario;

public interface IControladorUsuario {

    public Mensaje AltaAdministrador(String cedula, String nombre, String apellido, String contrasena);

    public Mensaje AltaComun(String cedula, String nombre, String apellido, String contrasena);

    public Mensaje AltaLector(String cedula, String nombre, String apellido, String contrasena);

    public Usuario ObtenerUsuarioxCedula(String cedula);
    
    public Mensaje BajaUsuario(String cedula);
    
    public Mensaje ModificarLector(String cedula, String nombre, String apellido, String contrasena, String nuevacedula);
    
    public Mensaje ModificarComun(String cedula, String nombre, String apellido, String contrasena, String nuevacedula);
    
    public Mensaje ModificarAdministrador(String cedula, String nombre, String apellido, String contrasena, String nuevacedula);
    
}
