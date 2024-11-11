package Clientes;

import MODELOS.Persona;

import java.util.Objects;

public class Cliente extends Persona {
    private String nacionalidad;
    private Domicilio domicilio;
    private String dni;

    public Cliente(String nombre, String apellido, String gmail, String nacionalidad, Domicilio domicilio, String dni) {
        super(nombre, apellido, gmail);
        this.nacionalidad = nacionalidad;
        this.domicilio = domicilio;
        this.dni = dni;
    }

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

    @Override
    public String toString() {
        return "Cliente{" +super.toString()+
                "nacionalidad='" + nacionalidad + '\'' +
                ", domicilio=" + domicilio +
                ", dni='" + dni + '\'' +
                '}';
    }
}
