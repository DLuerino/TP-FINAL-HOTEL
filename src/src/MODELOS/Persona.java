package MODELOS;

public class Persona {
    protected  String nombre;
    protected String apellido;
    protected String gmail;
    protected String contraseña;
    protected String tipoUsuario;

    /// -------------------------------------------------------------------------------------------------------------
    public Persona(String nombre, String apellido, String gmail, String contraseña ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.gmail = gmail;
        this.contraseña = contraseña;
    }

    public Persona() {
    }

    /// -------------------------------------------------------------------------------------------------------------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /// -------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", gmail='" + gmail + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                '}';
    }
    /// -------------------------------------------------------------------------------------------------------------

}
