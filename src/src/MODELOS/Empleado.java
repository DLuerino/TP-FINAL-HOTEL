package MODELOS;

import Enums.TipoEmpleado;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Empleado extends Persona{

    private int id;
    private static int ultimoId = 0;
    private String contraseña;
    private TipoEmpleado rol;

    public Empleado(String nombre, String apellido, String gmail, String contraseña, TipoEmpleado rol) {
        super(nombre, apellido, gmail);
        this.id = ultimoId++;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public Empleado(){
        this.id = ultimoId++;
    }

    public Empleado(int id)
    {
        this.id = id;
    }

    /// ---------------------------------------------------------------------------------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public TipoEmpleado getRol() {
        return rol;
    }

    public void setRol(TipoEmpleado rol) {
        this.rol = rol;
    }

    /// ---------------------------------------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(id, empleado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /// ---------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Empleado{" +super.toString()+
                "id='" + id + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", rol=" + rol +
                '}';
    }

    /// ---------------------------------------------------------------------------------------------------------------

    @Override
    public JSONObject toJSON() {
        JSONObject j = super.toJSON(); // Llama al método de la clase base Persona
        try {
            j.put("id", this.id);
            j.put("contraseña", this.contraseña);
            j.put("rol", this.rol.name());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return j;
    }

    public static Empleado fromJSON(JSONObject o) {
        Empleado empleado = new Empleado();
        try {

            empleado.setId(o.getInt("id"));
            empleado.setContraseña(o.getString("contraseña"));
            empleado.setRol(TipoEmpleado.valueOf(o.getString("rol")));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return empleado;
    }


}
