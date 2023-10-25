
package gui;

import controlador.Fachada;
import java.util.ArrayList;
import modelo.BaseDeDatos;
import modelo.Columna;
import modelo.Tabla;
import modelo.eRolUsuario;
import modelo.eTipoColumna;
import modelo.eVersionUsuario;


public class Inicio {
    
    
    public static void main(String[] args) {
        
        Fachada fachada = new Fachada();
        fachada.getControladorUsuario().altaUsuario("12345678", "Tito", "Apellido", "contrasena", eRolUsuario.ADMINISTRADOR, eVersionUsuario.DEMO);
        fachada.getControladorUsuario().altaUsuario("1234", "LECTOR", "LECTOR", "1234", eRolUsuario.LECTOR, eVersionUsuario.DEMO);
        fachada.getControladorUsuario().altaUsuario("4321", "COMUN", "COMUN", "1234", eRolUsuario.COMUN, eVersionUsuario.DEMO);
        
        fachada.getControladorBase().altaBase("prueba");
        BaseDeDatos prueba = fachada.getControladorBase().obtenerBaseXId(1);
        Columna nombre = new Columna("nombre", eTipoColumna.VARCHAR, true);
        Columna apellido = new Columna("apellido", eTipoColumna.VARCHAR, false);
        Columna edad = new Columna("edad", eTipoColumna.INT, true);
        ArrayList<Columna> columnas = new ArrayList<Columna>();
        columnas.add(nombre);
        columnas.add(apellido);
        columnas.add(edad);
        Tabla personas = new Tabla("personas", columnas);
        fachada.getControladorBase().agregarTabla(prueba, personas);
        
        Login login = new Login();
       
}}
