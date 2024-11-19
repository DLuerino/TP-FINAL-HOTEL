package MODELOS;

import Interfaces.IJSON;
import org.json.JSONException;
import org.json.JSONObject;

public class Administrador implements IJSON {
    private int id;
    private String nombre;
    private String contraseña;

    /// -----------------------------------------*-----------------------------------------

    public Administrador(int id, String nombre, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public Administrador() {
    }

    /// -----------------------------------------*-----------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /// -----------------------------------------*-----------------------------------------

    @Override
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        try {
            // Agregar los atributos de la clase Administrador al JSON
            j.put("id", this.id);
            j.put("nombre", this.nombre);
            j.put("contraseña", this.contraseña);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return j;
    }

    public static Administrador fromJSON(JSONObject o) {
        Administrador administrador = new Administrador();
        try {
            administrador.setNombre(o.getString("nombre"));
            administrador.setId(o.getInt("id"));
            administrador.setContraseña(o.getString("contraseña"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return administrador;
    }

}
