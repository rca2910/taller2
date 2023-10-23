package controlador;

import java.util.AbstractMap;
import java.util.ArrayList;
import modelo.BaseDeDatos;
import modelo.Celda;
import modelo.Columna;
import modelo.Mensaje;
import modelo.MensajeQuery;
import modelo.Sistema;
import modelo.Tabla;
import modelo.eTipoColumna;

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
                    return interpretarInsert(baseSeleccionada, sentencias);
                case "UPDATE":
                    return interpretarUpdate(baseSeleccionada, sentencias);
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

    private boolean valorValido(Columna columna, String valor)
    {
        if(valor.toUpperCase() == "NULL" && columna.isNulleable() == false)
        {
            return false;
        }

        if(valor == null || valor.isEmpty())
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

        if(columna.getTipo() == eTipoColumna.STRING)
        {
            if(valor.startsWith("'") && valor.endsWith("'"))
            {
                return true;
            }
            return false;
        }

        return false;
    }

    private void agregarValorAColumna(Columna columna, String valor)
    {
        int numeroCelda = columna.getCeldas().size();
        if(columna.getTipo() == eTipoColumna.STRING && valor.startsWith("'") && valor.endsWith("'") && valor.length() > 1)
        {
            valor = valor.substring(1, valor.length() - 1);
        }
        columna.getCeldas().add(new Celda(valor, numeroCelda));
    }

    private void agregarValorPorDefectoAColumna(Columna columna)
    {
        int numeroCelda = columna.getCeldas().size();
        String valorPorDefecto = obtenerValorPorDefecto(columna);
        columna.getCeldas().add(new Celda(valorPorDefecto, numeroCelda));
    }

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

    private void modificarValorTodasLasCeldas(Columna columna, String valor)
    {
        if(columna.getTipo() == eTipoColumna.STRING && valor.startsWith("'") && valor.endsWith("'") && valor.length() > 1)
        {
            valor = valor.substring(1, valor.length() - 1);
        }
        for(Celda c : columna.getCeldas())
        {
            c.setValor(valor);
        }
    }

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

    private boolean celdaCumpleCondicion(eTipoColumna tipo, Celda celda, AbstractMap.SimpleEntry<String, String> condicion)
    {
        if(condicion.getKey() == "NULL" && celda.getValor().equals(null))
        {
            return true;
        }

        if(condicion.getKey().equals("MAYOR"))
        {
            if(tipo == eTipoColumna.INT && Integer.parseInt(celda.getValor()) > Integer.parseInt(condicion.getValue()))
            {
                return true;
            }
            if(tipo == eTipoColumna.STRING)
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
            if(tipo == eTipoColumna.STRING)
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
            if(tipo == eTipoColumna.STRING)
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
            if(tipo == eTipoColumna.STRING)
            {
                return !celda.getValor().equals(condicion.getValue());
            }

            return false;
        }

        return false;
    }

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

    private MensajeQuery interpretarInsert(BaseDeDatos baseSeleccionada, String[] sentencias)
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
            if(!valorValido(columnaAVerificar, valoresAAgregar.get(i)))
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

    private MensajeQuery interpretarUpdate(BaseDeDatos baseSeleccionada, String[] sentencias)
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

        if(!valorValido(columnaAModificar, nuevoValor))
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

            ArrayList<Columna> columnasAConsiderar = new ArrayList<Columna>();
            ArrayList<AbstractMap.SimpleEntry<String, String>> condiciones = new ArrayList<AbstractMap.SimpleEntry<String, String>>();

            int posicionRecolectarColumna = 7;
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
                    return new MensajeQuery("La columna" + nombreColumna + " no existe en la tabla " + nombreTabla, false);
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

                if(!valorValido(columnaAConsiderar, valorAConsiderar))
                {
                    return new MensajeQuery("El valor de: " + valorAConsiderar + " no es valido para la columna " + columnaAConsiderar, false);
                }
                AbstractMap.SimpleEntry<String, String> condicion = new AbstractMap.SimpleEntry<String, String>(condicionAConsiderar, valorAConsiderar);
                condiciones.add(condicion);
            }
            ArrayList<String> nombresColumnasFiltradas = new ArrayList<String>();
            ArrayList<Celda> celdasCumplenCondicion = new ArrayList<Celda>();
            /*for(Columna c : columnasAConsiderar)
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
                    for(int i = 0; i<columnasAConsiderar.size(); i++)
                    {

                    }
                }
                nombresColumnasFiltradas.add(c.getNombre());
            }*/
        }
        else
        {
            modificarValorTodasLasCeldas(columnaAModificar, nuevoValor);
            caldasModificadas = columnaAModificar.getCeldas().size();
        }

        return new MensajeQuery("Ejecucion realizada con exito, se modificaron: " + caldasModificadas + " celdas", true);
    }
}
