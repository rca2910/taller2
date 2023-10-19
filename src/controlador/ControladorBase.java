package controlador;

import java.util.ArrayList;
import modelo.BaseDeDatos;
import modelo.Celda;
import modelo.Columna;
import modelo.Mensaje;
import modelo.MensajeQuery;
import modelo.Sistema;
import modelo.Tabla;

public class ControladorBase implements IControladorBase {

    //Crea una base de datos en el sistema
    
    public Mensaje altaBase(String nombre) {
        boolean nombreOcupado = nombreBaseOcupado(nombre);

        if (nombre.isEmpty()) 
        {
            return new Mensaje("Debe ingresar un nombre para la base de datos", false);
        }
        
        nombre = nombre.toUpperCase();
        if (nombreOcupado) 
        {
            return new Mensaje("Ya existe una base de datos con el nombre ingresado", false);
        } 
        else 
        {
            BaseDeDatos nueva = new BaseDeDatos(nombre);
            Sistema.getInstance().getBasesDeDatos().add(nueva);
            return new Mensaje("La base de datos fue creada exitosamente", true);
        }
    }

    // Da de baja una base de datos en el sistema.
    public Mensaje bajaBase(int id) {
        BaseDeDatos aBuscar = obtenerBaseXId(id);

        if ((aBuscar) == null) 
        {
            return new Mensaje("La base de datos no existe en el sistema", false);
        }
        
        Sistema.getInstance().getBasesDeDatos().remove(aBuscar);
        return new Mensaje("La base de datos fue eliminada exitosamente", true);

    }

    //Permite cambiar el nombre a una base de datos existente en el sistema.
    public Mensaje renombrarBase(int id, String nombre) {
        if (nombre.isEmpty()) {
            return new Mensaje("Debe ingresar un nombre para la base de datos", false);
        }
        
        BaseDeDatos aBuscar = obtenerBaseXId(id);

        if (aBuscar == null) {
            return new Mensaje("La base de datos no existe en el sistema", false);
        }

        nombre = nombre.toUpperCase();
        boolean nombreOcupado = nombreBaseOcupado(nombre);

        if (nombreOcupado) {
            return new Mensaje("El nombre ya esta siendo usado por otra base de datos", false);
        }

        aBuscar.setNombre(nombre);
        return new Mensaje("La base de datos fue modificada exitosamente", true);
    }

    //Realiza una búsqueda por ID para saber si existe una base de datos en el sistema, si existe la retorna.
    public BaseDeDatos obtenerBaseXId(int id) {

        for (BaseDeDatos b : Sistema.getInstance().getBasesDeDatos()) {
            if (id == (b.getId())) {

                return b;
            }
        }

        return null;
    }

    //Permite saber si un nombre de base de datos está disponible.
    public boolean nombreBaseOcupado(String nombre) {
        nombre = nombre.toUpperCase();
        for (BaseDeDatos b : Sistema.getInstance().getBasesDeDatos()) {
            if (nombre.toUpperCase().equals(b.getNombre())) {
                return true;
            }
        }
        return false;
    }

    //Carga la lista de base de datos que está en el sistema.
    public ArrayList<BaseDeDatos> obtenerBases() {
        return Sistema.getInstance().getBasesDeDatos();
    }

    //Permite agregar una tabla en una base de datos.
    public Mensaje agregarTabla(BaseDeDatos base, Tabla tabla) {
        Tabla tablaDisponible = obtenerTablaXNombre(base, tabla.getNombre());
        
        if (tablaDisponible != null) 
        {
            return new Mensaje("El nombre de la tabla ya existe en la base de datos", false);
        }
        
        base.getTablas().add(tabla);
        return new Mensaje("La tabla fue creada con exito", true);
    }

    //Permite agregar varias columnas de una vez en una tabla.
    public Mensaje agregarVariasColumnas(Tabla tabla, ArrayList<Columna> columnas) {
        for (Columna c : columnas) 
        {
            c.setNombre(c.getNombre().toUpperCase());
            if (!nombreColumnaDisponible(tabla, c.getNombre())) 
            {
                return new Mensaje("El nombre de la columna ya existe en la tabla", false);
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
        
        return new Mensaje("Las columnas fueron agregada exitosamente", true);
    }

    //Permite agregar una columna en una tabla
    public Mensaje agregarColumna(Tabla tabla, Columna columna) {
        columna.setNombre(columna.getNombre().toUpperCase());
        
        boolean nombreColumnaDisponible = nombreColumnaDisponible(tabla, columna.getNombre());
        if (!nombreColumnaDisponible) {
            return new Mensaje("El nombre de la columna ya existe en la tabla", false);
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
        return new Mensaje("La columna fue agregada exitosamente", true);
    }
    
    //Permite ejecutar una query dentro de una base de datos.
    public MensajeQuery ejecutarQuery(BaseDeDatos baseSeleccionada, String query)
    {
        if(query.isEmpty())
        {
            return new MensajeQuery("La query no puede estar vacia", false);
        }
        
        query = query.toUpperCase();
        String[] sentencias = query.split("\\s+");
        
        try{
            switch (sentencias[0]) {
                case "SELECT":
                    return interpretarSelect(baseSeleccionada, sentencias);
                case "CREATE":
                    return interpretarCreate(sentencias);
                case "DELETE":
                    return interpretarDelete(sentencias);
                case "INSERT":
                    return interpretarInsert(sentencias);
                case "UPDATE":
                    return interpretarUpdate(sentencias);
                default:
                    return new MensajeQuery("Error en la query en: " + sentencias[0], false);
            }
        }
        catch(Exception e){
            return new MensajeQuery("La sintaxis de la query parece incorrecta, por favor verifiquela", false);
        }
    }
    
    //Realiza una búsqueda por nombre para saber si existe una tabla en una base de datos. Si existe la retorna.
    private Tabla obtenerTablaXNombre(BaseDeDatos base, String nombreTabla){
        nombreTabla = nombreTabla.toUpperCase();
        
        for (Tabla t : base.getTablas()) {
            if (t.getNombre().equals(nombreTabla)) {
                return t;
            }
        }
        return null;
    }
    
    //Realiza una búsqueda por nombre para saber si existe una columna en una tabla. Si existe la retorna.
    private Columna obtenerColumnaXNombre(Tabla tabla, String nombreColumna)
    {
        nombreColumna = nombreColumna.toUpperCase();
        
        for (Columna c : tabla.getColumnas()) {
            if (c.getNombre().equals(nombreColumna)) {
                return c;
            }
        }
        return null;
    }

    //Permite saber si un nombre de columna está disponible dentro de la tabla
    private boolean nombreColumnaDisponible(Tabla tabla, String nombreColumna) {
        for (Columna c : tabla.getColumnas()) {
            if (c.getNombre().toUpperCase().equals(nombreColumna.toUpperCase())) {
                return false;
            }
        }
        return true;
    }

    //Permite saber si una tabla tiene datos ingresados o está vacía.
    private boolean tablaTieneDatos(Tabla aVerificar) {
        //Si no tiene columnas o no tiene datos
        if (aVerificar.getColumnas().isEmpty() || aVerificar.getColumnas().get(0).getCeldas().isEmpty()) {
            return false;
        }
        return true;
    }

    //Permite saber cuántas filas tienen las columnas de una tabla.
    private int cantidadDatosEnTabla(Tabla aVerificar) {
        if (aVerificar.getColumnas().isEmpty()) {
            return 0;
        } else {
            return aVerificar.getColumnas().get(0).getCeldas().size();
        }
    }

    //Obtiene los valores por defecto de una columna.
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
    
    //Permite ejecutar la query Select.
    private MensajeQuery interpretarSelect(BaseDeDatos baseSeleccionada, String[] sentencias)
    {
        int largoMinimo = 2;
        int posicionFrom = 2;
        int posicionPrimerTabla = 3;
        int posicionColumnaSeleccionada = 1;
        
        if (sentencias.length < largoMinimo) {
            return new MensajeQuery("La sentencia SELECT parece incompleta", false);
        }
        
        if(!sentencias[posicionFrom].equals("FROM"))
        {
            return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionFrom], false);
        }
        
        String nombreTabla = sentencias[posicionPrimerTabla];
        Tabla tablaSeleccionada = obtenerTablaXNombre(baseSeleccionada, nombreTabla);
        
        if(tablaSeleccionada == null)
        {
            return new MensajeQuery("La tabla: " + sentencias[posicionPrimerTabla] + "no existe en la base de datos", false);
        }
        
        String nombreColumna = sentencias[posicionColumnaSeleccionada];
        Columna columnaSeleccionada = obtenerColumnaXNombre(tablaSeleccionada, nombreColumna);
        
        if(columnaSeleccionada == null)
        {
            return new MensajeQuery("La columna: " + sentencias[posicionColumnaSeleccionada] + "no existe en la tabla seleccionada", false); 
        }
        
        ArrayList<Columna> columnasResultado = new ArrayList<Columna>();
        columnasResultado.add(columnaSeleccionada);//Esto es solo para probar
        
        return new MensajeQuery("Sentencia ejecutada correctamente", true, columnasResultado);
    }
    
    //Permite ejecutar la query Create.
    private MensajeQuery interpretarCreate(String[] sentencias)
    {
        return new MensajeQuery("No implementado aun", false);
    }
    
    //Permite ejecutar la query Delete.
    private MensajeQuery interpretarDelete(String[] sentencias)
    {
        return new MensajeQuery("No implementado aun", false);
    }
    
    //Permite ejecutar la query Insert.
    private MensajeQuery interpretarInsert(String[] sentencias)
    {
        return new MensajeQuery("No implementado aun", false);
    }
    
    //Permite ejecutar la query Update.
    private MensajeQuery interpretarUpdate(String[] sentencias)
    {
        return new MensajeQuery("No implementado aun", false);
    }
}
