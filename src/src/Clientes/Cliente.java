package Clientes;

import Excepciones.DniIngresoException;
import Excepciones.ErrorAlIngresarException;
import MODELOS.Persona;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Objects;

public class Cliente extends Persona{
    private String nacionalidad;
    private Domicilio domicilio;
    private String dni;

    public Cliente(String nombre, String apellido, String gmail, String nacionalidad, Domicilio domicilio, String dni) {
        super(nombre, apellido, gmail);
        this.nacionalidad = nacionalidad;
        this.domicilio = domicilio;
        this.dni = dni;
    }

    public Cliente() {
    }

    public Cliente(String nombre)
    {
        this.nombre = nombre;
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
        return "\n╔═════════════════════════════════════════════╗" +
                "\n║                  CLIENTE                    ║" +
                "\n╠═════════════════════════════════════════════╣" +
                "\n DNI:            " + String.format("%-35s", this.dni) +
                "\n Nombre:         " + String.format("%-35s", this.nombre) +
                "\n Apellido:       " + String.format("%-35s", this.apellido) +
                "\n Gmail:          " + String.format("%-35s", this.gmail) +
                "\n Nacionalidad:   " + String.format("%-35s", this.nacionalidad) +
                "\n Domicilio      " + String.format("%-35s", this.domicilio.toString()) +
                "\n╚═════════════════════════════════════════════╝";
    }

    public void verificarCliente() throws ErrorAlIngresarException,DniIngresoException {

        if(this.nombre.isEmpty() || this.apellido.isEmpty() || this.nacionalidad.isEmpty() || this.dni.isEmpty()  || this.gmail.isEmpty()){
            throw new ErrorAlIngresarException("\n Complete el espacio vacio. ");
        }
        if(dni.length()<8)
        {
           throw new DniIngresoException("El dni ingresado no contiene la cantidad de numeros necesarios");
        }
        if(!dni.matches("\\d{8}"))
        {
            throw new DniIngresoException("El dni ingresado no puede contener espacios o letras.");
        }
    }

    /// -----------------------------------------------------------------------------------------------------------------

    @Override
    public JSONObject toJSON() {
        JSONObject j = super.toJSON(); /// Llama al método ObjAJson de Persona
        try {
            j.put("nacionalidad", this.nacionalidad);
            j.put("domicilio", this.domicilio.toJSON());
            j.put("dni", this.dni);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return j;
    }

    public static Cliente fromJSON(JSONObject o) {
        Cliente cliente = new Cliente();
        try {
            cliente.setNombre(o.getString("nombre"));
            cliente.setApellido(o.getString("apellido"));
            cliente.setGmail(o.getString("correoElectronico"));
            cliente.setDni(o.getString("dni"));
            cliente.setDomicilio(Domicilio.fromJSON(o.getJSONObject("domicilio")));
            cliente.setNacionalidad(o.getString("nacionalidad"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cliente;
    }
}
