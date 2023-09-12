
package modelo;


public class Mensaje {
    
    private String mensaje;
    private boolean exito;
    

    public Mensaje(String mensaje, boolean exito) {
        this.mensaje = mensaje;
        this.exito = exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public boolean isExito() {
        return exito;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }
    
    
    
}
