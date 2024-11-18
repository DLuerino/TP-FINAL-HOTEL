package MODELOS;

public class Persona {
    protected  String nombre;
    protected String apellido;
    protected String gmail;
    protected String contraseña;
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


    /// -------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", gmail='" + gmail + '\'' +
                ", contraseña='" + contraseña + '\'';
    }
    /// -------------------------------------------------------------------------------------------------------------

}
