package Clientes;

import Excepciones.ErrorAlIngresarException;
import MODELOS.Persona;
import org.json.JSONException;
import org.json.JSONObject;

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

        if(this.nombre.isEmpty() || this.apellido.isEmpty() || this.nacionalidad.isEmpty() || this.dni.isEmpty()  || this.gmail.isEmpty()){
            throw new ErrorAlIngresarException("\n Complete el espacio vacio. ");
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
        Cliente cliente = null;
        try {
            /// Usar el método JsonAObj de Persona para atributos base
            Persona persona = Persona.fromJSON(o);

            /// Obtener atributos específicos de Cliente
            String nacionalidad = o.getString("nacionalidad");
            String dni = o.getString("dni");

            /// Convertir el domicilio si existe
            Domicilio domicilio = null;
            if (!o.isNull("domicilio")) {
                JSONObject domicilioJson = o.getJSONObject("domicilio");
                domicilio = Domicilio.fromJSON(domicilioJson);
            }

            // Crear el objeto Cliente con datos de Persona y atributos adicionales
            cliente = new Cliente(persona.getNombre(), persona.getApellido(), persona.getGmail(), nacionalidad, domicilio, dni);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cliente;
    }
}
