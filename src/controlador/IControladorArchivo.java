package controlador;

import java.io.IOException;

public interface IControladorArchivo {
    public void GuardarUsuarios() throws IOException;
    public void GuardarBasesDeDatos() throws IOException;
    public void CargarUsuarios() throws IOException, ClassNotFoundException;
    public void CargarBasesDeDatos() throws IOException, ClassNotFoundException;
}
