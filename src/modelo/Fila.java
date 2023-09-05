
package modelo;


public class Fila {
    
    private int id;
    private String dato;
    private int numero;

    public Fila(int id, String dato, int numero) {
        this.id = id;
        this.dato = dato;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public String getDato() {
        return dato;
    }

    public int getNumero() {
        return numero;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    
    
}
