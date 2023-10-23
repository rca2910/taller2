package modelo;

public class Celda {

    private String valor;
    private int numero;

    //Constructor
    
    public Celda(String dato, int numero) {
        this.valor = dato;
        this.numero = numero;
    }

    // Getters y Setters 
    
    public String getValor() {
        return valor;
    }

    public int getNumero() {
        return numero;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

}
