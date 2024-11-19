package MODELOS;

import Interfaces.IJSON;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Persona implements IJSON {
    protected  String nombre;
    protected String apellido;
    protected String gmail;
    /// -------------------------------------------------------------------------------------------------------------
    public Persona(String nombre, String apellido, String gmail) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.gmail = gmail;
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

    /// -------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", gmail='" + gmail + '\'' ;
    }

    /// -------------------------------------------------------------------------------------------------------------

    @Override
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        try {
            j.put("nombre", this.nombre);
            j.put("apellido", this.apellido);
            j.put("correoElectronico", this.gmail);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return j;
    }


}
