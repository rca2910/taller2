package controlador;

import java.util.ArrayList;
import modelo.BaseDeDatos;
import modelo.Celda;
import modelo.Columna;
import modelo.Mensaje;
import modelo.Sistema;
import modelo.Tabla;

public class ControladorBase implements IControladorBase {

    public Mensaje altaBase(String nombre) {
        Mensaje respuesta = new Mensaje("", false);

        boolean nombreOcupado = nombreBaseOcupado(nombre);

        if (nombre.isEmpty()) {
            respuesta.setMensaje("Debe ingresar un nombre para la base de datos");
            return respuesta;
        }
        if (nombreOcupado) {
            respuesta.setMensaje("Ya existe una base de datos con el nombre ingresado");
        } else {
            BaseDeDatos nueva = new BaseDeDatos(nombre);
            Sistema.getInstance().getBasesDeDatos().add(nueva);
            respuesta.setMensaje("La base de datos fue creada exitosamente");
            respuesta.setExito(true);
        }

        return respuesta;
    }

    public Mensaje bajaBase(int id) {
        BaseDeDatos aBuscar = obtenerBaseXId(id);
        Mensaje respuesta = new Mensaje("", false);

        if ((aBuscar) != null) {
            Sistema.getInstance().getBasesDeDatos().remove(aBuscar);

            respuesta.setMensaje("La base de datos fue eliminada exitosamente");
            respuesta.setExito(true);
            return respuesta;
        } else {
            respuesta.setMensaje("La base de datos no existe en el sistema");
            return respuesta;
        }

    }

    public Mensaje renombrarBase(int id, String nombre) {
        Mensaje respuesta = new Mensaje("", false);
        BaseDeDatos aBuscar = obtenerBaseXId(id);

        if (aBuscar == null) {
            respuesta.setMensaje("La base de datos no existe en el sistema");
            return respuesta;
        }

        boolean nombreOcupado = nombreBaseOcupado(nombre);

        if (nombreOcupado) {
            respuesta.setMensaje("El nombre ya esta siendo usado por otra base de datos");
            return respuesta;
        }

        aBuscar.setNombre(nombre.toUpperCase());
        respuesta.setMensaje("La base de datos fue modificada exitosamente");
        respuesta.setExito(true);

        return respuesta;
    }

    public BaseDeDatos obtenerBaseXId(int id) {

        for (BaseDeDatos b : Sistema.getInstance().getBasesDeDatos()) {
            if (id == (b.getId())) {

                return b;
            }
        }

        return null;
    }

    public boolean nombreBaseOcupado(String nombre) {
        for (BaseDeDatos b : Sistema.getInstance().getBasesDeDatos()) {
            if (nombre.toUpperCase().equals(b.getNombre())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<BaseDeDatos> obtenerBases() {
        return Sistema.getInstance().getBasesDeDatos();
    }

    public Mensaje agregarTabla(BaseDeDatos base, Tabla tabla) {
        Mensaje respuesta = new Mensaje("", false);

        boolean nombreTablaDisponible = nombreTablaDisponible(base, tabla.getNombre());
        if (!nombreTablaDisponible) {
            respuesta.setMensaje("El nombre de la tabla ya existe en la base de datos");
            return respuesta;
        }
        base.getTablas().add(tabla);

        respuesta.setMensaje("La tabla fue agregada exitosamente");
        respuesta.setExito(true);
        return respuesta;
    }

    public Mensaje agregarVariasColumnas(Tabla tabla, ArrayList<Columna> columnas) {
        Mensaje respuesta = new Mensaje("", false);

        for (Columna c : columnas) {
            if (!nombreColumnaDisponible(tabla, c.getNombre())) {
                respuesta.setMensaje("El nombre de la columna ya existe en la tabla");
                return respuesta;
            }
        }

        int cantidadCeldasARellenar = 0;

        if (tablaTieneDatos(tabla)) {
            cantidadCeldasARellenar = cantidadDatosEnTabla(tabla);
        }

        for (Columna c : columnas) {
            int contador = 0;
            while (contador < cantidadCeldasARellenar) {
                String dato = obtenerValorPorDefecto(c);
                Celda nuevaCelda = new Celda(dato, contador);
                c.getCeldas().add(nuevaCelda);
                contador++;
            }

            tabla.getColumnas().add(c);
        }

        respuesta.setMensaje("Las columnas fueron agregada exitosamente");
        respuesta.setExito(true);
        return respuesta;
    }

    public Mensaje agregarColumna(Tabla tabla, Columna columna) {
        Mensaje respuesta = new Mensaje("", false);

        boolean nombreColumnaDisponible = nombreColumnaDisponible(tabla, columna.getNombre());
        if (!nombreColumnaDisponible) {
            respuesta.setMensaje("El nombre de la columna ya existe en la tabla");
            return respuesta;
        }

        if (tablaTieneDatos(tabla)) {
            int cantidadCeldasARellenar = cantidadDatosEnTabla(tabla);
            int contador = 0;
            while (contador < cantidadCeldasARellenar) {
                String dato = obtenerValorPorDefecto(columna);
                Celda nuevaCelda = new Celda(dato, contador);
                columna.getCeldas().add(nuevaCelda);
                contador++;
            }
        }

        tabla.getColumnas().add(columna);
        respuesta.setMensaje("La columna fue agregada exitosamente");
        respuesta.setExito(true);
        return respuesta;
    }

    private boolean nombreTablaDisponible(BaseDeDatos base, String nombreTabla) {
        for (Tabla t : base.getTablas()) {
            if (t.getNombre().toUpperCase().equals(nombreTabla.toUpperCase())) {
                return false;
            }
        }
        return true;
    }

    private boolean nombreColumnaDisponible(Tabla tabla, String nombreColumna) {
        for (Columna c : tabla.getColumnas()) {
            if (c.getNombre().toUpperCase().equals(nombreColumna.toUpperCase())) {
                return false;
            }
        }
        return true;
    }

    private boolean tablaTieneDatos(Tabla aVerificar) {
        //Si no tiene columnas o no tiene datos
        if (aVerificar.getColumnas().isEmpty() || aVerificar.getColumnas().get(0).getCeldas().isEmpty()) {
            return false;
        }
        return true;
    }

    private int cantidadDatosEnTabla(Tabla aVerificar) {
        if (aVerificar.getColumnas().isEmpty()) {
            return 0;
        } else {
            return aVerificar.getColumnas().get(0).getCeldas().size();
        }
    }

    private String obtenerValorPorDefecto(Columna columna) {
        if (columna.isNulleable()) {
            return null;
        }
        switch (columna.getTipo()) {
            case INT:
                return "0";
            default:
                return "";
        }
    }
}
