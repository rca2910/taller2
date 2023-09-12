package modelo;

public class Fila {

    private String dato;
    private int numero;

    public Fila(String dato, int numero) {

        this.dato = dato;
        this.numero = numero;
    }

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
