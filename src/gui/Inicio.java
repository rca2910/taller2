
package gui;

import controlador.ControladorUsuario;
import controlador.IControladorUsuario;
import modelo.eRolUsuario;
import modelo.eVersionUsuario;


public class Inicio {
    
    
    public static void main(String[] args) {
        
        IControladorUsuario controladorUsuario = new ControladorUsuario();
        controladorUsuario.altaUsuario("12345678", "Tito", "Apellido", "contrasena", eRolUsuario.ADMINISTRADOR, eVersionUsuario.DEMO);
        controladorUsuario.altaUsuario("1234", "LECTOR", "LECTOR", "1234", eRolUsuario.LECTOR, eVersionUsuario.DEMO);
        controladorUsuario.altaUsuario("4321", "COMUN", "COMUN", "1234", eRolUsuario.COMUN, eVersionUsuario.DEMO);
        
        Login login = new Login();
       
}}
