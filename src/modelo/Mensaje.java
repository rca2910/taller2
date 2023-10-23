
package modelo;


public class Mensaje {
    
    private String mensaje;
    private boolean exito;
    

    // Constructor
    
    public Mensaje(String mensaje, boolean exito) {
        this.mensaje = mensaje;
        this.exito = exito;
    }

    // Getters y Setters
    
    public String getMensaje() {
        return mensaje;
    }

    public boolean isExito() {
        return exito;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
