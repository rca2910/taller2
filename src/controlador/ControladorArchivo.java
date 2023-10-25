package controlador;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import modelo.BaseDeDatos;
import modelo.Sistema;
import modelo.Usuario;

public class ControladorArchivo implements IControladorArchivo {
    private FileOutputStream archivoGuardar;
    private FileInputStream archivoCargar;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private ControladorUsuario controladorUsuario = new ControladorUsuario();
    private ControladorBase controladorBase = new ControladorBase();
    
    //Permite escribir los datos de los usuarios en un archivo.
    public void GuardarUsuarios() throws IOException
    {
        for(Usuario u : Sistema.getInstance().getUsuarios())
        {
            this.abrirArchivoGuardarUsuarios();
            this.escribirUsuarios(u);
            this.cerrar();
        }
    }
    
    //Permite escribir las bases de datos en un archivo.
    public void GuardarBasesDeDatos() throws IOException
    {
        for(BaseDeDatos b : Sistema.getInstance().getBasesDeDatos())
        {
            this.abrirArchivoGuardarBasesDeDatos();
            this.escribirBasesDeDatos(b);
            this.cerrar();
        }
    }
    
    //Permite cargar el archivo de usuarios en el sistema.
    public void CargarUsuarios() throws IOException, ClassNotFoundException
    {
        Usuario usuario;
        
        this.abrirArchivoCargarUsuarios();
        
        do{
            usuario = this.leerUsuario();
            if(usuario != null)
            {
                Usuario aVerificar = controladorUsuario.ObtenerUsuarioxCedula(usuario.getCedula());
                if(aVerificar == null)
                {
                    Sistema.getInstance().getUsuarios().add(usuario);
                }
            }
            
        }
        while(usuario != null);
        
        this.cerrar();
    }
    
    //Permite cargar el archivo de base de datos en el sistema.
    public void CargarBasesDeDatos() throws IOException, ClassNotFoundException
    {
        BaseDeDatos baseDeDatos;
        
        this.abrirArchivoCargarBasesDeDatos();
        
        do{
            baseDeDatos = this.leerBaseDeDatos();
            if(baseDeDatos != null)
            {
                BaseDeDatos aVerificar = controladorBase.obtenerBaseXId(baseDeDatos.getId());
                if(aVerificar == null)
                {
                    Sistema.getInstance().getBasesDeDatos().add(baseDeDatos);
                }
            }
        }
        while(baseDeDatos != null);
        
        this.cerrar();
    }
    
    //Permite guardar los datos de los usuarios en un archivo.
    private void abrirArchivoGuardarUsuarios() throws IOException
    {
        archivoGuardar = new FileOutputStream( "usuarios.dat" );
        salida = new ObjectOutputStream(archivoGuardar);
    }
    
    //Carga el archivo de usuarios.
    public void abrirArchivoCargarUsuarios() throws IOException
    {
        archivoCargar = new FileInputStream( "usuarios.dat" );
        entrada = new ObjectInputStream (archivoCargar);
    }
    
    //Permite guardar los datos de las bases de datos en un archivo
    private void abrirArchivoGuardarBasesDeDatos() throws IOException
    {
        archivoGuardar = new FileOutputStream( "bases_de_datos.dat" );
        salida = new ObjectOutputStream(archivoGuardar);
    }
    
    //Carga el archivo de base de datos.
    private void abrirArchivoCargarBasesDeDatos() throws IOException
    {
        archivoCargar = new FileInputStream( "bases_de_datos.dat" );
        entrada = new ObjectInputStream(archivoCargar);
    }
    
    //Escribe los datos de un usuario.
    private void escribirUsuarios (Usuario usuario) throws IOException
    {
        if (salida!=null)
            salida.writeObject(usuario);
    }
    
    //Escribe los datos de una base de datos.
    private void escribirBasesDeDatos (BaseDeDatos baseDeDatos) throws IOException
    {
        if (salida!=null)
            salida.writeObject(baseDeDatos);
    }
    
    //Cierra la conexión con el archivo.
    
    private void cerrar() throws IOException
    {
        if (salida!=null)
            salida.close();
    }
    
    //Devuelve los datos de un usuario.
    private Usuario leerUsuario () throws IOException, ClassNotFoundException
    {
        Usuario usuario = null;
        if (entrada!=null) 
        {
            try {
                usuario = (Usuario) entrada.readObject();
            } 
            catch (EOFException eof) 
            {
            }
        }
        return usuario;
    }
    
    //Devuelve los datos de una base de datos.
    private BaseDeDatos leerBaseDeDatos () throws IOException, ClassNotFoundException
    {
        BaseDeDatos baseDeDatos = null;
        if (entrada!=null) 
        {
            try {
                baseDeDatos = (BaseDeDatos) entrada.readObject();
            } 
            catch (EOFException eof) 
            {
            }
        }
        return baseDeDatos;
    }
}
