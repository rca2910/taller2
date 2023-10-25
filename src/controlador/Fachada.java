package controlador;

public class Fachada {
    private IControladorBase controladorBase;
    private IControladorUsuario controladorUsuario;
    private IControladorArchivo controladorArchivo;
    
    public Fachada()
    {
        this.controladorBase = new ControladorBase();
        this.controladorUsuario = new ControladorUsuario();
        this.controladorArchivo = new ControladorArchivo();
    }
    
    public IControladorBase getControladorBase()
    {
        return this.controladorBase;
    }
    
    public IControladorUsuario getControladorUsuario()
    {
        return this.controladorUsuario;
    }
    
    public IControladorArchivo getControladorArchivo()
    {
        return this.controladorArchivo;
    }
}
