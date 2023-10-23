
package gui;

import controlador.ControladorBase;
import controlador.ControladorUsuario;
import controlador.IControladorBase;
import controlador.IControladorUsuario;
import java.util.ArrayList;
import modelo.BaseDeDatos;
import modelo.Columna;
import modelo.Tabla;
import modelo.eRolUsuario;
import modelo.eTipoColumna;
import modelo.eVersionUsuario;


public class Inicio {
    
    
    public static void main(String[] args) {
        
        IControladorUsuario controladorUsuario = new ControladorUsuario();
        controladorUsuario.altaUsuario("12345678", "Tito", "Apellido", "contrasena", eRolUsuario.ADMINISTRADOR, eVersionUsuario.DEMO);
        controladorUsuario.altaUsuario("1234", "LECTOR", "LECTOR", "1234", eRolUsuario.LECTOR, eVersionUsuario.DEMO);
        controladorUsuario.altaUsuario("4321", "COMUN", "COMUN", "1234", eRolUsuario.COMUN, eVersionUsuario.DEMO);
        
        IControladorBase controladorBase = new ControladorBase();
        controladorBase.altaBase("prueba");
        BaseDeDatos prueba = controladorBase.obtenerBaseXId(1);
        Columna nombre = new Columna("nombre", eTipoColumna.STRING, true);
        Columna apellido = new Columna("apellido", eTipoColumna.STRING, false);
        Columna edad = new Columna("edad", eTipoColumna.INT, true);
        ArrayList<Columna> columnas = new ArrayList<Columna>();
        columnas.add(nombre);
        columnas.add(apellido);
        columnas.add(edad);
        Tabla personas = new Tabla("personas", columnas);
        controladorBase.agregarTabla(prueba, personas);
        
        Login login = new Login();
       
}}
