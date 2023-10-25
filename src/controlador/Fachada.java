package controlador;

public class Fachada {
    private IControladorBase controladorBase;
    private IControladorUsuario controladorUsuario;
    private IControladorArchivo controladorArchivo;
    
    //Constructor.
    public Fachada()
    {
        this.controladorBase = new ControladorBase();
        this.controladorUsuario = new ControladorUsuario();
        this.controladorArchivo = new ControladorArchivo();
    }
    
    //Devuelve la interface de ControladorBase.
    public IControladorBase getControladorBase()
    {
        return this.controladorBase;
    }
    
    //Devuelve la interface de ControladorUsuario.
    public IControladorUsuario getControladorUsuario()
    {
        return this.controladorUsuario;
    }
    
    //Devuelve la interface de ControladorArchivo.
    public IControladorArchivo getControladorArchivo()
    {
        return this.controladorArchivo;
    }
}
