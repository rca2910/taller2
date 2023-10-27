
package modelo;

import java.io.Serializable;


public enum eTipoColumna implements Serializable {
    INT, VARCHAR, DATE, BOOL;
    
    public static eTipoColumna[] valoresLimitados() {
        return new eTipoColumna[] { INT, VARCHAR };
    }
}
