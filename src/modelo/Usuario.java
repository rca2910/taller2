
package modelo;


public class Usuario {
    
    private String cedula;
    private String nombre;
    private String apellido;
    private String contrasena;
    private eRolUsuario rol;

    public Usuario(String cedula, String nombre, String apellido, String contrasena,eRolUsuario rol) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRol(eRolUsuario rol) {
        this.rol = rol;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public eRolUsuario getRol() {
        return rol;
    }
 
    
}

