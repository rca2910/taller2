
package gui;

import controlador.ControladorUsuario;
import modelo.eRolUsuario;


public class Inicio {
    
    
    public static void main(String[] args) {
        
        ControladorUsuario controladorUsuario = new ControladorUsuario();
        controladorUsuario.AltaUsuario("12345678", "Tito", "Apellido", "contrasena", eRolUsuario.ADMINISTRADOR);
        
        Login login = new Login();
       
}}
