package modelo;

import java.util.ArrayList;

public class MensajeQuery {
    private String mensaje;
    private boolean exito;
    private ArrayList<Columna> columnas;
    

    public MensajeQuery(String mensaje, boolean exito) {
        this.mensaje = mensaje;
        this.exito = exito;
        this.columnas = new ArrayList<Columna>();
    }
    
    public MensajeQuery(String mensaje, boolean exito, ArrayList<Columna> columnas) {
        this.mensaje = mensaje;
        this.exito = exito;
        this.columnas = columnas;
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

    public ArrayList<Columna> getColumnas() {
        return columnas;
    }

    public void setColumnas(ArrayList<Columna> columnas) {
        this.columnas = columnas;
    }
}
