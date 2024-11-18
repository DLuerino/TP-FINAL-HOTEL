package Clientes;

import Excepciones.ErrorAlIngresarException;
import MODELOS.Persona;

import java.util.Objects;

public class Cliente extends Persona {
    private String nacionalidad;
    private Domicilio domicilio;
    private String dni;

    public Cliente(String nombre, String apellido, String gmail, String contraseña, String tipoUsuario, String nacionalidad, Domicilio domicilio, String dni) {
        super(nombre, apellido, gmail, contraseña);
        this.nacionalidad = nacionalidad;
        this.domicilio = domicilio;
        this.dni = dni;
    }

    public Cliente() {
    }

    /// ---------------------------------------------------------------------------------------------------------------


    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    /// -----------------------------------------------------------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return Objects.equals(getDni(), cliente.getDni());
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(getDni());
    }
    /// -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Cliente{" +super.toString()+
                "nacionalidad='" + nacionalidad + '\'' +
                ", domicilio=" + domicilio +
                ", dni='" + dni + '\'' +
                '}';
    }

    public void verificarCliente() throws ErrorAlIngresarException {

        if(this.nombre == null){
            throw new ErrorAlIngresarException("\n Complete el espacio vacio con un nombre. ");
        }
        if(this.apellido == null){
            throw new ErrorAlIngresarException("\n Complete el espacio vacio con un apellido. ");
        }
        if(this.nacionalidad == null){
            throw new ErrorAlIngresarException("\n Complete el espacio vacio con una nacionalidad. ");
        }
        if(this.dni == null){
            throw new ErrorAlIngresarException("\n Complete el espacio vacio con un DNI. ");
        }
        if(this.domicilio == null){
            throw new ErrorAlIngresarException("\n Complete el espacio vacio con un domicilio. ");
        }
        if(this.contraseña == null){
            throw new ErrorAlIngresarException("\n Complete el espacio vacio con una contraseña valida. ");
        }
        if(this.gmail == null){
            throw new ErrorAlIngresarException("\n Complete el espacio vacio con un gmail. ");
        }

    }

}
