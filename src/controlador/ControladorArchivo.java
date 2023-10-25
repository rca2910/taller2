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
    
    public void GuardarUsuarios() throws IOException
    {
        for(Usuario u : Sistema.getInstance().getUsuarios())
        {
            this.abrirArchivoGuardarUsuarios();
            this.escribirUsuarios(u);
            this.cerrar();
        }
    }
    
    public void GuardarBasesDeDatos() throws IOException
    {
        for(BaseDeDatos b : Sistema.getInstance().getBasesDeDatos())
        {
            this.abrirArchivoGuardarBasesDeDatos();
            this.escribirBasesDeDatos(b);
            this.cerrar();
        }
    }
    
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
    
    private void abrirArchivoGuardarUsuarios() throws IOException
    {
        archivoGuardar = new FileOutputStream( "usuarios.dat" );
        salida = new ObjectOutputStream(archivoGuardar);
    }
    
    public void abrirArchivoCargarUsuarios() throws IOException
    {
        archivoCargar = new FileInputStream( "usuarios.dat" );
        entrada = new ObjectInputStream (archivoCargar);
    }
    
    private void abrirArchivoGuardarBasesDeDatos() throws IOException
    {
        archivoGuardar = new FileOutputStream( "bases_de_datos.dat" );
        salida = new ObjectOutputStream(archivoGuardar);
    }
    
    private void abrirArchivoCargarBasesDeDatos() throws IOException
    {
        archivoCargar = new FileInputStream( "bases_de_datos.dat" );
        entrada = new ObjectInputStream(archivoCargar);
    }
    
    private void escribirUsuarios (Usuario usuario) throws IOException
    {
        if (salida!=null)
            salida.writeObject(usuario);
    }
    
    private void escribirBasesDeDatos (BaseDeDatos baseDeDatos) throws IOException
    {
        if (salida!=null)
            salida.writeObject(baseDeDatos);
    }
    
    private void cerrar() throws IOException
    {
        if (salida!=null)
            salida.close();
    }
    
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
