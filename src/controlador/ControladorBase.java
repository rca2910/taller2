package controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import modelo.BaseDeDatos;
import modelo.Celda;
import modelo.Columna;
import modelo.Mensaje;
import modelo.MensajeQuery;
import modelo.Sistema;
import modelo.Tabla;
import modelo.eRolUsuario;
import modelo.eTipoColumna;
import modelo.eVersionUsuario;

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
    public MensajeQuery ejecutarQuery(int idBaseSeleccionada, String query, eVersionUsuario versionUsuario, eRolUsuario rolUsuario)
    {
        BaseDeDatos baseSeleccionada = obtenerBaseXId(idBaseSeleccionada);
        if(baseSeleccionada == null)
        {
            return new MensajeQuery("La base no existe en el sistema", false);
        }
        if(query.isEmpty())
        {
            return new MensajeQuery("La query no puede estar vacia", false);
        }

        query = query.toUpperCase();
        String[] sentencias = query.split("\\s+");

        try{
            if(rolUsuario == eRolUsuario.LECTOR && (sentencias[0].equals("CREATE")|| sentencias[0].equals("UPDATE") || sentencias[0].equals("DELETE") || sentencias[0].equals("INSERT")))
            {
                return new MensajeQuery("Los usuarios lectores, solo pueden ejecutar comandos de lectura", false);
            }
            switch (sentencias[0]) {
                case "SELECT":
                    return interpretarSelect(baseSeleccionada, sentencias, versionUsuario);
                case "CREATE":
                    return interpretarCreate(baseSeleccionada, sentencias, versionUsuario);
                case "DELETE":
                    return interpretarDelete(baseSeleccionada, sentencias, versionUsuario);
                case "INSERT":
                    return interpretarInsert(baseSeleccionada, sentencias, versionUsuario);
                case "UPDATE":
                    return interpretarUpdate(baseSeleccionada, sentencias, versionUsuario);
                case "SHOW":
                    return mostrarTablas(baseSeleccionada, sentencias);
                case "DESCRIBE":
                    return describeTabla(baseSeleccionada, sentencias);
                case "HELP":
                    return mostrarAyuda(sentencias);
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
    
    //Permite borrar todos los datos de una tabla.
    private void vaciarTabla(Tabla aVaciar)
    {
        for(Columna c : aVaciar.getColumnas())
        {
            c.setCeldas(new ArrayList<Celda>());
        }
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
            case DATE:
                return "0001-01-01";
            case BOOL:
                return "FALSE";
            default:
                return "";
        }
    }

    //Controla que los datos ingresados en la columna sean del tipo correcto.
    private boolean valorValido(Columna columna, String valor, eVersionUsuario versionUsuario)
    {
        if(valor == null && columna.isNulleable())
        {
            return true;
        }

        if(columna.getTipo() == eTipoColumna.INT)
        {
            try{
                Integer.parseInt(valor);
                return true;
            }
            catch(Exception e){
                return false;
            }
        }

        if(columna.getTipo() == eTipoColumna.VARCHAR)
        {
            if(valor.startsWith("'") && valor.endsWith("'"))
            {
                return true;
            }
            return false;
        }
        
        if(columna.getTipo() == eTipoColumna.BOOL && versionUsuario == eVersionUsuario.FULL)
        {
            if(valor.equals("TRUE") || valor.equals("FALSE"))
            {
                return true;
            }
            return false;
        }
        
        if(columna.getTipo() == eTipoColumna.DATE && versionUsuario == eVersionUsuario.FULL)
        {
            String valorAux = valor;
            if(valorAux.startsWith("'") && valorAux.endsWith("'"))
            {
                valorAux = valorAux.substring(1, valorAux.length() - 1);
            }
            else
            {
                return false;
            }
            DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try 
            {
                LocalDate.parse(valorAux, formateador);
                return true;
            } 
            catch (DateTimeParseException e) 
            {
                return false;
            }
        }

        return false;
    }

    //Permite agregar un valor a una columna
    private void agregarValorAColumna(Columna columna, String valor)
    {
        int numeroCelda = columna.getCeldas().size();
        if(columna.getTipo() == eTipoColumna.VARCHAR && valor.startsWith("'") && valor.endsWith("'") && valor.length() > 1)
        {
            valor = valor.substring(1, valor.length() - 1);
        }
        columna.getCeldas().add(new Celda(valor, numeroCelda));
    }

    //Permite agregar los valores por defecto a una columna.
    private void agregarValorPorDefectoAColumna(Columna columna)
    {
        int numeroCelda = columna.getCeldas().size();
        String valorPorDefecto = obtenerValorPorDefecto(columna);
        columna.getCeldas().add(new Celda(valorPorDefecto, numeroCelda));
    }

    //Devuelve un listado de las celdas que cumplen una condición.
    private ArrayList<Celda> obtenerCeldasXCondiciones(Columna columna, ArrayList<AbstractMap.SimpleEntry<String, String>> condiciones)
    {
        ArrayList<Celda> celdasEncontradas = new ArrayList<Celda>();
        boolean primerChequeoRealizado = false;
        for(AbstractMap.SimpleEntry<String, String> condicion : condiciones)
        {
            if(primerChequeoRealizado == false)
            {
                for(Celda c: columna.getCeldas())
                {
                    if(celdaCumpleCondicion(columna.getTipo(), c, condicion))
                    {
                        celdasEncontradas.add(c);
                    }
                }
                primerChequeoRealizado = true;
            }
            else
            {
                for(Celda c : celdasEncontradas)
                {
                    if(!celdaCumpleCondicion(columna.getTipo(), c, condicion))
                    {
                        celdasEncontradas.remove(c);
                    }
                }
            }
        }
        return celdasEncontradas;
    }

    //Permite cambiar el tipo de dato de todas las celdas de una columna.
    private void modificarValorTodasLasCeldas(Columna columna, String valor)
    {
        if(columna.getTipo() == eTipoColumna.VARCHAR && valor.startsWith("'") && valor.endsWith("'") && valor.length() > 1)
        {
            valor = valor.substring(1, valor.length() - 1);
        }
        for(Celda c : columna.getCeldas())
        {
            c.setValor(valor);
        }
    }
    
    //Permite cambiar de lugar la celda en una columna.
    private void modificarValorNumeroCelda(Columna columna, String valor, int numeroCelda)
    {
        if(columna.getTipo() == eTipoColumna.VARCHAR && valor.startsWith("'") && valor.endsWith("'") && valor.length() > 1)
        {
            valor = valor.substring(1, valor.length() - 1);
        }
        for(Celda c : columna.getCeldas())
        {
            if(c.getNumero() == numeroCelda)
            {
                c.setValor(valor);
                return;
            }
        }
    }
    
    //Permite eliminar la posición de una celda en la columna.
    private void eliminarNumeroCelda(Tabla tabla, int numeroCelda)
    {
        for(Columna c : tabla.getColumnas())
        {
            Iterator<Celda> celdaIterator = c.getCeldas().iterator();
            while(celdaIterator.hasNext())
            {
                Celda celda = celdaIterator.next();
                if(celda.getNumero() == numeroCelda)
                {
                    celdaIterator.remove();
                }
            }
        }
    }

    //Permite elegir la condición.
    private String formatearCondicion(String condicion)
    {
        switch(condicion)
        {
            case ">":
                return "MAYOR";
            case "<":
                return "MENOR";
            case "=":
                return "IGUAL";
            case "<>":
                return "DISTINTO";
            default:
                return null;
        }
    }
    
    //Permite saber el tipo de dato que tiene la columna.
    private eTipoColumna obtenerTipoColumnaXNombre(String nombreTipoColumna)
    {
        switch(nombreTipoColumna.toUpperCase())
        {
            case "INT":
                return eTipoColumna.INT;
            case "VARCHAR":
                return eTipoColumna.VARCHAR;
            case "BOOL":
                return eTipoColumna.BOOL;
            case "DATE":
                return eTipoColumna.DATE;
            default:
                return null;
        }
    }
    
    //Retorna las celdas que pertenecen a una fila.
    private Celda obtenerCeldaXNumero(Columna columna, int numeroCelda)
    {
        for(Celda celda : columna.getCeldas())
        {
            if(celda.getNumero() == numeroCelda)
            {
                return celda;
            }
        }
        return null;
    }
    
    //Retorna el valor de celda más alto.
    private Celda obtenerMayorCelda(Columna columna)
    {
        if(columna.getTipo() != eTipoColumna.INT)
        {
            return null;
        }
        Celda mayor = null;
        for(Celda celda : columna.getCeldas())
        {
            if(mayor == null || Integer.parseInt(celda.getValor()) > Integer.parseInt(mayor.getValor()))
            {
                mayor = celda;
            }
        }
        
        return mayor;
    }
    
    //Retorna el valor de celda más bajo.
    private Celda obtenerMenorCelda(Columna columna)
    {
        if(columna.getTipo() != eTipoColumna.INT)
        {
            return null;
        }
        Celda menor = null;
        for(Celda celda : columna.getCeldas())
        {
            if(menor == null || Integer.parseInt(celda.getValor()) < Integer.parseInt(menor.getValor()))
            {
                menor = celda;
            }
        }
        
        return menor;
    }

    //Permite aplicar una condición a una celda.
    private boolean celdaCumpleCondicion(eTipoColumna tipo, Celda celda, AbstractMap.SimpleEntry<String, String> condicion)
    {
        if(condicion.getKey() == "NULL" && celda.getValor().equals(null))
        {
            return true;
        }
        
        if(tipo == eTipoColumna.VARCHAR && condicion.getValue().startsWith("'") && condicion.getValue().endsWith("'") && condicion.getValue().length() > 1)
        {
            condicion.setValue( condicion.getValue().substring(1, condicion.getValue().length() - 1));
        }

        if(condicion.getKey().equals("MAYOR"))
        {
            if(tipo == eTipoColumna.INT && Integer.parseInt(celda.getValor()) > Integer.parseInt(condicion.getValue()))
            {
                return true;
            }
            if(tipo == eTipoColumna.VARCHAR)
            {
                return celda.getValor().compareTo(condicion.getValue()) > 0;
            }

            return false;
        }

        if(condicion.getKey().equals("MENOR"))
        {
            if(tipo == eTipoColumna.INT && Integer.parseInt(celda.getValor()) < Integer.parseInt(condicion.getValue()))
            {
                return true;
            }
            if(tipo == eTipoColumna.VARCHAR)
            {
                return celda.getValor().compareTo(condicion.getValue()) < 0;
            }

            return false;
        }

        if(condicion.getKey().equals("IGUAL"))
        {
            if(tipo == eTipoColumna.INT && Integer.parseInt(celda.getValor()) == Integer.parseInt(condicion.getValue()))
            {
                return true;
            }
            if(tipo == eTipoColumna.VARCHAR)
            {
                return celda.getValor().equals(condicion.getValue());
            }

            return false;
        }

        if(condicion.getKey().equals("DISTINTO"))
        {
            if(tipo == eTipoColumna.INT && Integer.parseInt(celda.getValor()) != Integer.parseInt(condicion.getValue()))
            {
                return true;
            }
            if(tipo == eTipoColumna.VARCHAR)
            {
                return !celda.getValor().equals(condicion.getValue());
            }

            return false;
        }

        return false;
    }

    //Controla los campos para poder usar la query Select.
    private MensajeQuery interpretarSelect(BaseDeDatos baseSeleccionada, String[] sentencias, eVersionUsuario versionUsuario)
    {
        if(versionUsuario == eVersionUsuario.FULL)
        {
            if((sentencias[1].startsWith("MAX(") || sentencias[1].startsWith("MIN(")) && sentencias[1].endsWith(")"))
            {
                if(sentencias.length > 4)
                {
                    return new MensajeQuery("Verifique las sentencias, MAX y MIN solo estan permitidos para 1 tabla", false);
                }
                
                int posicionFrom = 2;
                int posicionTabla = 3;
                boolean esMax = false;
                
                if(!sentencias[posicionFrom].equals("FROM"))
                {
                    return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionFrom] + " recuerde que esta usando una version demo", false);
                }
                
                if(sentencias[1].startsWith("MAX("))
                {
                    esMax = true;
                }
                
                String nombreTabla = sentencias[posicionTabla];
                
                Tabla tablaSeleccionada = obtenerTablaXNombre(baseSeleccionada, nombreTabla);
                if(tablaSeleccionada == null)
                {
                    return new MensajeQuery("La tabla: " + sentencias[posicionTabla] + " no existe en la base de datos", false);
                }
                
                String nombreColumna = sentencias[1].substring(4, sentencias[1].length() - 1);
                Columna columnaSeleccionada = obtenerColumnaXNombre(tablaSeleccionada, nombreColumna);
            
                if(columnaSeleccionada == null)
                {
                    return new MensajeQuery("La columna: " + nombreColumna + " no existe en la tabla seleccionada", false);
                }
                
                if(esMax)
                {
                    return interpretarMax(columnaSeleccionada);
                }
                else
                {
                    return interpretarMin(columnaSeleccionada);
                }
            }
            
            int posicionActual = 1;
            ArrayList<String> nombresColumnasSeleccionadas = new ArrayList<String>();
            
            while(!sentencias[posicionActual].equals("FROM"))
            {
                nombresColumnasSeleccionadas.add(sentencias[posicionActual]);
                posicionActual++;
            }
            posicionActual++;
            ArrayList<Tabla> tablasSeleccionadas = new ArrayList<Tabla>();
            while(!sentencias[posicionActual].equals("WHERE") || sentencias.length < posicionActual)
            {
                String nombreTablaSeleccionada = sentencias[posicionActual];
                Tabla tablaSeleccionada = obtenerTablaXNombre(baseSeleccionada, nombreTablaSeleccionada);
                if(tablaSeleccionada == null)
                {
                    return new MensajeQuery("La tabla: " + nombreTablaSeleccionada + " no existe en la base de datos", false);
                }
                tablasSeleccionadas.add(tablaSeleccionada);
            }
            
            //ArrayList<String> nombresTablasSe
            
            return new MensajeQuery("La sentencia SELECT parece incompleta", false);
        }
        else
        {
            int largoMinimo = 4;
            if (sentencias.length < largoMinimo) 
            {
                return new MensajeQuery("La sentencia SELECT parece incompleta", false);
            }
            
            int posicionColumna = 1;
            int posicionFrom = 2;
            int posicionTabla = 3;
            
            if(!sentencias[posicionFrom].equals("FROM"))
            {
                return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionFrom] + " recuerde que esta usando una version demo", false);
            }
            
            String nombreTabla = sentencias[posicionTabla];
            Tabla tablaSeleccionada = obtenerTablaXNombre(baseSeleccionada, nombreTabla);
            
            if(tablaSeleccionada == null)
            {
                return new MensajeQuery("La tabla: " + sentencias[posicionTabla] + " no existe en la base de datos", false);
            }
            
            String nombreColumna = sentencias[posicionColumna];
            Columna columnaSeleccionada = obtenerColumnaXNombre(tablaSeleccionada, nombreColumna);
            
            if(columnaSeleccionada == null)
            {
                return new MensajeQuery("La columna: " + sentencias[posicionColumna] + " no existe en la tabla seleccionada", false);
            }
            
            ArrayList<Columna> columnasResultado = new ArrayList<Columna>();
            
            if(sentencias.length == largoMinimo)
            {
                columnasResultado.add(columnaSeleccionada);
                return new MensajeQuery("Sentencia ejecutada correctamente", true, columnasResultado);
            }
            
            int posicionWhere = 4;
            largoMinimo = 8;
            
            if(!sentencias[posicionFrom].equals("WHERE"))
            {
                return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionWhere] + " recuerde que esta usando una version demo", false);
            }
            
            if(sentencias.length > largoMinimo)
            {
                columnasResultado.add(columnaSeleccionada);
                return new MensajeQuery("Recuerde que esta usando una version demo, solo se puede utilizar 1 sentencia WHERE", false);
            }
            
            int posicionColumnaFiltro = 5;
            String nombreColumnaFiltro = sentencias[posicionColumnaFiltro];
            Columna columnaFiltro = obtenerColumnaXNombre(tablaSeleccionada, nombreColumnaFiltro);
            
            if(columnaFiltro == null)
            {
                return new MensajeQuery("La columna: " + sentencias[posicionColumnaFiltro] + " no existe en la tabla seleccionada", false);
            }
            
            int posicionCondicionFiltro = 6;
            if(!sentencias[posicionCondicionFiltro].equals("="))
            {
                return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionCondicionFiltro] + " recuerde que esta usando una version demo", false);
            }
            String condicionAConsiderar = formatearCondicion(sentencias[posicionCondicionFiltro]);
            
            int posicionValorFiltro = 7;
            
            String valorAConsiderar = sentencias[posicionValorFiltro];
            if(!valorValido(columnaFiltro, valorAConsiderar, versionUsuario))
            {
                return new MensajeQuery("Verifique que valor de: " + valorAConsiderar + " sea valido para la columna, recuerde que su version solo filtra por int o varchar ", false);
            }
            AbstractMap.SimpleEntry<String, String> condicion = new AbstractMap.SimpleEntry<String, String>(condicionAConsiderar, valorAConsiderar);
            ArrayList<AbstractMap.SimpleEntry<String, String>> condiciones = new ArrayList<AbstractMap.SimpleEntry<String, String>>();
            condiciones.add(condicion);
            ArrayList<Celda> celdasFiltradas = obtenerCeldasXCondiciones(columnaFiltro, condiciones);
            Columna columnaARetornar = new Columna(columnaSeleccionada.getNombre(), columnaSeleccionada.getTipo(), columnaSeleccionada.isNulleable());
            for(Celda c : celdasFiltradas)
            {
                Celda celdaFiltrada = obtenerCeldaXNumero(columnaSeleccionada, c.getNumero());
                columnaARetornar.getCeldas().add(celdaFiltrada);
            }
            columnasResultado.add(columnaARetornar);
            return new MensajeQuery("Sentencia ejecutada correctamente", true, columnasResultado);
        }
    }

    //Permite ejecutar la query Create.
    private MensajeQuery interpretarCreate(BaseDeDatos baseSeleccionada, String[] sentencias, eVersionUsuario versionUsuario)
    {
        int posicionTable = 1;
        int posicionNombreTabla = 2;
        int posicionColumna = 3;
        int posicionTipoColumna = 4;
        
        if(!sentencias[posicionTable].equals("TABLE"))
        {
            return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionTable], false);
        }
        
        String nombreTablaACrear = sentencias[posicionNombreTabla];
        Tabla tablaACrear = obtenerTablaXNombre(baseSeleccionada, nombreTablaACrear);
        
        if(tablaACrear != null)
        {
            return new MensajeQuery("La tabla con el nombre: " + nombreTablaACrear + " ya existe en la base de datos", false);
        }
        
        tablaACrear = new Tabla(nombreTablaACrear);
        
        if(sentencias[posicionColumna].equals("("))
        {
            posicionColumna++;
            posicionTipoColumna++;
        }
        else if(sentencias[posicionColumna].startsWith("("))
        {
            sentencias[posicionColumna] = sentencias[posicionColumna].substring(1);
        }
        
        boolean finalizRecoleccionColumnas = false;
        
        while(finalizRecoleccionColumnas == false)
        {
            String nombreColumna = sentencias[posicionColumna];
            if(sentencias[posicionTipoColumna].endsWith(","))
            {
                sentencias[posicionTipoColumna] = sentencias[posicionTipoColumna].substring(0, sentencias[posicionTipoColumna].length() - 1);
            }
            else if(sentencias[posicionTipoColumna].endsWith(")"))
            {
                sentencias[posicionTipoColumna] = sentencias[posicionTipoColumna].substring(0, sentencias[posicionTipoColumna].length() - 1);
                finalizRecoleccionColumnas = true;
            }
            else if(sentencias[posicionTipoColumna].endsWith(");"))
            {
                sentencias[posicionTipoColumna] = sentencias[posicionTipoColumna].substring(0, sentencias[posicionTipoColumna].length() - 2);
                finalizRecoleccionColumnas = true;
            }
            else
            {
                return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionTipoColumna], false);
            }
            
            eTipoColumna tipoColumna = obtenerTipoColumnaXNombre(sentencias[posicionTipoColumna]);
            if(tipoColumna == null)
            {
                return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionTipoColumna], false);
            }
            
            Columna nuevaColumna = new Columna(nombreColumna, tipoColumna, false);
            Mensaje agregarColumna = agregarColumna(tablaACrear, nuevaColumna);
            if(!agregarColumna.isExito())
            {
                return new MensajeQuery("Verifique que no existan columnas repetidas", false);
            }
            
            posicionColumna += 2;
            posicionTipoColumna+= 2;
        }
        
        agregarTabla(baseSeleccionada, tablaACrear);
        
        return new MensajeQuery("La tabla fue creada correctamente", true);
    }

    //Permite ejecutar la query Delete.
    private MensajeQuery interpretarDelete(BaseDeDatos baseSeleccionada, String[] sentencias, eVersionUsuario versionUsuario)
    {
        int posicionFrom = 1;
        int posicionTabla = 2;

        if(!sentencias[posicionFrom].equals("FROM"))
        {
            return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionFrom], false);
        }
        
        String nombreTabla = sentencias[posicionTabla].trim();
        
        if(sentencias.length == 3)
        {
            if(nombreTabla.endsWith(";"))
            {
                nombreTabla = nombreTabla.substring(0, nombreTabla.length() - 1);
            }
            
            Tabla tablaABorrar = obtenerTablaXNombre(baseSeleccionada, nombreTabla);
            if(tablaABorrar == null)
            {
                return new MensajeQuery("La tabla" + nombreTabla +  " no existe en la base de datos", false);
            }
            vaciarTabla(tablaABorrar);
            return new MensajeQuery("La tabla" + nombreTabla +  " fue vaciada", true);
        }
        
        int posicionWhere = 3;

        if(!sentencias[posicionWhere].equals("WHERE"))
        {
            return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionWhere], false);
        }

        Tabla tablaABorrar = obtenerTablaXNombre(baseSeleccionada, nombreTabla);

        if(tablaABorrar == null)
        {
            return new MensajeQuery("La tabla" + nombreTabla +  " no existe en la base de datos", false);
        }
        
        ArrayList<Celda> celdasCumplenCondicion = new ArrayList<Celda>();
        MensajeQuery mensajeInterpretarWhere = interpretarWhere(sentencias, tablaABorrar, 4, celdasCumplenCondicion, versionUsuario);
        
        if(!mensajeInterpretarWhere.isExito())
        {
            return mensajeInterpretarWhere;
        }
        
        ArrayList<Integer> numerosCeldasCumplenCondicion = new ArrayList<Integer>();
        for(Celda c : celdasCumplenCondicion)
        {
            if(!numerosCeldasCumplenCondicion.contains(c.getNumero()))
            {
                numerosCeldasCumplenCondicion.add(c.getNumero());
            }
        }
        for(int numeroCelda : numerosCeldasCumplenCondicion)
        {
            eliminarNumeroCelda(tablaABorrar, numeroCelda);
        }

        return new MensajeQuery("Ejecucion realizada con exito, se eliminaron: " + numerosCeldasCumplenCondicion.size() + " celdas", true);
    }

    //Permite ejecutar la query Insert.
    private MensajeQuery interpretarInsert(BaseDeDatos baseSeleccionada, String[] sentencias, eVersionUsuario versionUsuario)
    {
        int posicionInto = 1;
        int posicionTabla = 2;
        int posicionColumnas = 3;

        if(!sentencias[posicionInto].equals("INTO"))
        {
            return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionInto], false);
        }

        String nombreTabla = sentencias[posicionTabla].trim();

        ArrayList<String> nombresColumnas = new ArrayList<String>();
        String primeraColumna = sentencias[posicionColumnas].replaceAll("[\\(,\\)]", "");

        nombresColumnas.add(primeraColumna);

        int cantidadColumnas = 1;

        if(!sentencias[posicionColumnas].endsWith(")"))
        {
            boolean finalizacionChequeoColumnas = false;

            while(finalizacionChequeoColumnas == false)
            {
                String nuevaColumna = sentencias[posicionColumnas + cantidadColumnas];
                if(nuevaColumna.endsWith(")"))
                {
                    finalizacionChequeoColumnas = true;
                }

                nuevaColumna = nuevaColumna.replaceAll("[,)]$", "").trim();
                nombresColumnas.add(nuevaColumna);
                cantidadColumnas++;
            }
        }

        int posicionValues = posicionColumnas + cantidadColumnas;
        int posicionValores = posicionValues+1;

        if(!sentencias[posicionValues].equals("VALUES"))
        {
            return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionValues], false);
        }

        ArrayList<String> valoresAAgregar = new ArrayList<String>();
        boolean finalizacionChequeoValores = false;

        while(finalizacionChequeoValores == false)
        {
            String nuevoValor = sentencias[posicionValores];
            if(nuevoValor.endsWith(")") || nuevoValor.endsWith(";"))
            {
                finalizacionChequeoValores = true;
            }
            nuevoValor = nuevoValor.replaceAll("[\\(,\\);]", "").trim();
            if (nuevoValor.startsWith("\"") || nuevoValor.endsWith("\""))
            {
                return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionValores], false);
            }
            valoresAAgregar.add(nuevoValor);
            posicionValores++;
        }

        Tabla tablaAModificar = obtenerTablaXNombre(baseSeleccionada, nombreTabla);

        if(tablaAModificar == null)
        {
            return new MensajeQuery("La tabla no existe en la base de datos", false);
        }

        if(nombresColumnas.size() != valoresAAgregar.size())
        {
            return new MensajeQuery("La cantidad de columnas no coincide con los valores a agregar", false);
        }

        for(int i = 0; i < nombresColumnas.size(); i++)
        {
            Columna columnaAVerificar = obtenerColumnaXNombre(tablaAModificar, nombresColumnas.get(i));
            if(columnaAVerificar == null)
            {
                return new MensajeQuery("La columna: " + nombresColumnas.get(i) + " no existe en la tabla " + nombreTabla, false);
            }
            if(!valorValido(columnaAVerificar, valoresAAgregar.get(i), versionUsuario))
            {
                return new MensajeQuery("El valor de: " + valoresAAgregar.get(i) + " no es valido para la columna " + nombresColumnas.get(i), false);
            }
        }

        for(Columna c : tablaAModificar.getColumnas())
        {
            boolean valorAgregado = false;
            for(int i = 0; i < nombresColumnas.size(); i++)
            {
                if(valorAgregado == true)
                {
                    continue;
                }
                if(c.getNombre().equals(nombresColumnas.get(i)))
                {
                    agregarValorAColumna(c, valoresAAgregar.get(i));
                    valorAgregado = true;
                }
            }
            if(valorAgregado == false)
            {
                agregarValorPorDefectoAColumna(c);
            }
        }

        return new MensajeQuery("Valores agregados correctamente", true);
    }

    //Permite ejecutar la query Update.
    private MensajeQuery interpretarUpdate(BaseDeDatos baseSeleccionada, String[] sentencias, eVersionUsuario versionUsuario)
    {
        int posicionTabla = 1;
        int posicionSet = 2;
        int posicionColumna = 3;
        int posicionIgual = 4;
        int posicionValorAModificar = 5;

        String nombreTabla = sentencias[posicionTabla].trim();

        if(!sentencias[posicionSet].equals("SET"))
        {
            return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionSet], false);
        }

        String nombreColumna = sentencias[posicionColumna].trim();

        if(!sentencias[posicionIgual].equals("="))
        {
            return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionIgual], false);
        }

        String nuevoValor = sentencias[posicionValorAModificar].trim();

        Tabla tablaAModificar = obtenerTablaXNombre(baseSeleccionada, nombreTabla);

        if(tablaAModificar == null)
        {
            return new MensajeQuery("La tabla" + nombreTabla +  " no existe en la base de datos", false);
        }

        Columna columnaAModificar = obtenerColumnaXNombre(tablaAModificar, nombreColumna);

        if(columnaAModificar == null)
        {
            return new MensajeQuery("La columna" + nombreColumna + " no existe en la tabla " + nombreTabla, false);
        }

        if(!valorValido(columnaAModificar, nuevoValor, versionUsuario))
        {
            return new MensajeQuery("El valor de: " + nuevoValor + " no es valido para la columna " + nombreColumna, false);
        }

        boolean tieneWhere = sentencias.length > posicionValorAModificar +1;
        int caldasModificadas = 0;

        if(tieneWhere)
        {
            int posicionWhere = 6;
            if(!sentencias[posicionWhere].equals("WHERE"))
            {
                return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionWhere], false);
            }

            ArrayList<Celda> celdasCumplenCondicion = new ArrayList<Celda>();
            
            MensajeQuery mensajeInterpretarWhere = interpretarWhere(sentencias, tablaAModificar, 7, celdasCumplenCondicion, versionUsuario);
            
            if(!mensajeInterpretarWhere.isExito())
            {
                return mensajeInterpretarWhere;
            }
            
            ArrayList<Integer> numerosCeldasCumplenCondicion = new ArrayList<Integer>();
            for(Celda c : celdasCumplenCondicion)
            {
                if(!numerosCeldasCumplenCondicion.contains(c.getNumero()))
                {
                    numerosCeldasCumplenCondicion.add(c.getNumero());
                }
            }
            for(int numeroCelda : numerosCeldasCumplenCondicion)
            {
                modificarValorNumeroCelda(columnaAModificar, nuevoValor, numeroCelda);
                caldasModificadas++;
            }
        }
        else
        {
            modificarValorTodasLasCeldas(columnaAModificar, nuevoValor);
            caldasModificadas = columnaAModificar.getCeldas().size();
        }

        return new MensajeQuery("Ejecucion realizada con exito, se modificaron: " + caldasModificadas + " celdas", true);
    }
    
    //Permite aplicar la query Where.
    private MensajeQuery interpretarWhere(String[] sentencias, Tabla tablaAModificar, int posicionInicial, ArrayList<Celda> celdasCumplenCondicion, eVersionUsuario versionUsuario)
    {
        ArrayList<Columna> columnasAConsiderar = new ArrayList<Columna>();
        ArrayList<AbstractMap.SimpleEntry<String, String>> condiciones = new ArrayList<AbstractMap.SimpleEntry<String, String>>();

        int posicionRecolectarColumna = posicionInicial;
        boolean condicionesRecolectadas = false;
        while(condicionesRecolectadas == false)
        {
            int posicionRecolectarCondicion = posicionRecolectarColumna +1;
            if(sentencias[posicionRecolectarCondicion].equals("IS"))
            {
                posicionRecolectarCondicion++;
            }
            int posicionRecolectarValor = posicionRecolectarCondicion +1;

            Columna columnaAConsiderar = obtenerColumnaXNombre(tablaAModificar, sentencias[posicionRecolectarColumna]);
            if(columnaAConsiderar == null)
            {
                return new MensajeQuery("La columna" + sentencias[posicionRecolectarColumna] + " no existe en la tabla " + tablaAModificar.getNombre(), false);
            }
            columnasAConsiderar.add(columnaAConsiderar);

            String condicionAConsiderar = formatearCondicion(sentencias[posicionRecolectarCondicion]);
            if(condicionAConsiderar == null)
            {
                return new MensajeQuery("Verifique la condicion: " + sentencias[posicionRecolectarCondicion], false);
            }

            String valorAConsiderar = sentencias[posicionRecolectarValor];

            if(sentencias.length == posicionRecolectarValor +1)
            {
            if(valorAConsiderar.endsWith(";"))
            {
                valorAConsiderar = valorAConsiderar.substring(0, valorAConsiderar.length() - 1);
            }
            condicionesRecolectadas = true;
            }
            else
            {
                posicionRecolectarColumna = posicionRecolectarValor +1;
                if(!sentencias[posicionRecolectarColumna].equals("AND"))
                {
                    return new MensajeQuery("Verifique la sentencia en: " + sentencias[posicionRecolectarColumna], false);
                }
                }

                if(!valorValido(columnaAConsiderar, valorAConsiderar, versionUsuario))
                {
                    return new MensajeQuery("El valor de: " + valorAConsiderar + " no es valido para la columna " + columnaAConsiderar, false);
                }
                AbstractMap.SimpleEntry<String, String> condicion = new AbstractMap.SimpleEntry<String, String>(condicionAConsiderar, valorAConsiderar);
                condiciones.add(condicion);
        }
            ArrayList<String> nombresColumnasFiltradas = new ArrayList<String>();
            for(Columna c : columnasAConsiderar)
            {
                boolean columnaYaFiltrada = false;
                for(String cf : nombresColumnasFiltradas)
                {
                    if(cf.equals(c.getNombre()))
                    {
                        columnaYaFiltrada = true;
                    }
                }
                if(columnaYaFiltrada == false)
                {
                    ArrayList<AbstractMap.SimpleEntry<String, String>> condicionesAux = new ArrayList<AbstractMap.SimpleEntry<String, String>>();
                    for(int i = 0; i<condiciones.size(); i++)
                    {
                        if(columnasAConsiderar.get(i).getNombre() == c.getNombre())
                        {
                            condicionesAux.add(condiciones.get(i));
                        }
                    }
                    celdasCumplenCondicion.addAll(obtenerCeldasXCondiciones(c, condicionesAux));
                    nombresColumnasFiltradas.add(c.getNombre());
                }
            }
            
            return new MensajeQuery("Where interpretado con exito", true);
    }
    
    //Permite aplicar la query Max.
    private MensajeQuery interpretarMax(Columna columnaSeleccionada)
    {
        Celda mayorCelda = obtenerMayorCelda(columnaSeleccionada);
        ArrayList<Columna> columnasResultado = new ArrayList<Columna>();
        Columna columnaRetorno = new Columna(columnaSeleccionada.getNombre() +" (MAX)", columnaSeleccionada.getTipo(), columnaSeleccionada.isNulleable());
        if(mayorCelda == null)
        {
            columnasResultado.add(columnaRetorno);
            return new MensajeQuery("Verifique que la columna sea numerica y tenga algun valor", false, columnasResultado);
        }
        else
        {
            ArrayList<Celda> celdasAAgregar = new ArrayList<Celda>();
            columnaRetorno.setCeldas(celdasAAgregar);
            return new MensajeQuery("Max interpretado con exito", true, columnasResultado);
        }
    }
    
    //Permite aplicar la query Min.
    private MensajeQuery interpretarMin(Columna columnaSeleccionada)
    {
        Celda menorCelda = obtenerMenorCelda(columnaSeleccionada);
        ArrayList<Columna> columnasResultado = new ArrayList<Columna>();
        Columna columnaRetorno = new Columna(columnaSeleccionada.getNombre() +" (MIN)", columnaSeleccionada.getTipo(), columnaSeleccionada.isNulleable());
        if(menorCelda == null)
        {
            columnasResultado.add(columnaRetorno);
            return new MensajeQuery("Verifique que la columna sea numerica y tenga algun valor", false, columnasResultado);
        }
        else
        {
            ArrayList<Celda> celdasAAgregar = new ArrayList<Celda>();
            columnaRetorno.setCeldas(celdasAAgregar);
            return new MensajeQuery("Min interpretado con exito", true, columnasResultado);
        }
    }
    
    private MensajeQuery mostrarTablas(BaseDeDatos baseSeleccionada, String[] sentencias)
    {
        ArrayList<Columna> columnasResultado = new ArrayList<Columna>();
        if(sentencias.length != 2 || !sentencias[1].equals("TABLES"))
        {
            return new MensajeQuery("Verifique la sintaxis de SHOW TABLES", false);
        }
        for(Tabla t : baseSeleccionada.getTablas())
        {
            columnasResultado.add(new Columna(t.getNombre(), eTipoColumna.VARCHAR, true));
        }
        return new MensajeQuery("Show tables interpretado con exito", true, columnasResultado);
    }
    
    private MensajeQuery describeTabla(BaseDeDatos baseSeleccionada, String[] sentencias)
    {
        ArrayList<Columna> columnasResultado = new ArrayList<Columna>();
        if(sentencias.length != 2)
        {
            return new MensajeQuery("Verifique la sintaxis de DESCRIBE, debe contener solo 1 tabla", false);
        }
        String nombreTabla = sentencias[1];
        Tabla tablaSeleccionada = obtenerTablaXNombre(baseSeleccionada, nombreTabla);
        
        if(tablaSeleccionada == null)
        {
            return new MensajeQuery("La tabla: " + nombreTabla + " no existe en la base de datos", false);
        }
        
        Columna nombre = new Columna("Nombre columna", eTipoColumna.VARCHAR, true);
        Columna tipo = new Columna("Tipo columna", eTipoColumna.VARCHAR, true);
        Columna nulleable = new Columna("Nulleable", eTipoColumna.VARCHAR, true);
        int numeroCelda = 0;
        for(Columna c : tablaSeleccionada.getColumnas())
        {
            Celda celdaNombre = new Celda(c.getNombre(), numeroCelda);
            Celda celdaTipo = new Celda(c.getTipo().name(), numeroCelda);
            String columnaEsNulleable = "FALSE";
            if(c.isNulleable())
            {
                columnaEsNulleable = "TRUE";
            }
            Celda celdaNulleable = new Celda(columnaEsNulleable, numeroCelda);
            
            nombre.getCeldas().add(celdaNombre);
            tipo.getCeldas().add(celdaTipo);
            nulleable.getCeldas().add(celdaNulleable);
            
            numeroCelda++;
        }
        
        columnasResultado.add(nombre);
        columnasResultado.add(tipo);
        columnasResultado.add(nulleable);
        
        return new MensajeQuery("Describe interpretado con exito", true, columnasResultado);
    }
    
    private MensajeQuery mostrarAyuda(String[] sentencias)
    {
        ArrayList<Columna> columnasResultado = new ArrayList<Columna>();
        if(sentencias.length != 2)
        {
            return new MensajeQuery("HELP contiene las siguientes acciones disponibles:\n SELECT, CREATE, UPDATE, DELETE, INSERT"
                    + "SHOW, DESCRIBE \n Ejemplo: SHOW SELECT", false);
        }
        
        switch(sentencias[1])
        {
        case "SELECT":
            return new MensajeQuery("La query SELECT en SQL recupera datos de una tabla.\nEjemplo: SELECT nombre, edad FROM Empleados;", true);
        
        case "UPDATE":
            return new MensajeQuery("La query UPDATE actualiza datos existentes en una tabla.\nEjemplo: UPDATE Empleados SET edad = 30 WHERE nombre = 'Juan';", true);

        case "CREATE":
            return new MensajeQuery("La query CREATE crea una nueva tabla o base de datos.\nEjemplo: CREATE TABLE Empleados (nombre VARCHAR(50), edad INT);", true);

        case "DELETE":
            return new MensajeQuery("La query DELETE elimina registros de una tabla.\nEjemplo: DELETE FROM Empleados WHERE nombre = 'Juan';", true);

        case "INSERT":
            return new MensajeQuery("La query INSERT agrega nuevos registros a una tabla.\nEjemplo: INSERT INTO Empleados (nombre, edad) VALUES ('Juan', 25);", true);

        case "DESCRIBE":
            return new MensajeQuery("La query DESCRIBE muestra la estructura de una tabla.\nEjemplo: DESCRIBE Empleados;", true);

        case "SHOW":
            return new MensajeQuery("La query SHOW lista bases de datos o tablas.\nEjemplo: SHOW TABLES;", true);

        default:
            return new MensajeQuery("HELP contiene las siguientes acciones disponibles:\n SELECT, CREATE, UPDATE, DELETE, INSERT, SHOW, DESCRIBE \n Ejemplo: HELP SELECT", false);
}
    }
}
