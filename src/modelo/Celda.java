package modelo;

public class Celda {

    private String dato;
    private int numero;

    //Constructor
    
    public Celda(String dato, int numero) {
        this.dato = dato;
        this.numero = numero;
    }

    // Getters y Setters 
    
    public String getDato() {
        return dato;
    }

    public int getNumero() {
        return numero;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
