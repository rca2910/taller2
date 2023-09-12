
package modelo;


public class Administrador extends Usuario{
    
    public Administrador(String cedula, String nombre, String apellido, String contrasena) {
        super(cedula, nombre, apellido, contrasena);
    }
    
    public Administrador (){
        super ("","","","");
    }
}
