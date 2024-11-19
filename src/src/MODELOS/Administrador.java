package MODELOS;

import Interfaces.metodoJson;
import org.json.JSONException;
import org.json.JSONObject;

public class Administrador implements metodoJson {
    private int id;
    private String nombre;
    private String contraseña;

    /// -----------------------------------------*-----------------------------------------

    public Administrador(int id, String nombre, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
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
    public JSONObject ObjAJson() {
        JSONObject j = new JSONObject();
        try {
            // Agregar los atributos de la clase Administrador al JSON
            j.put("id", this.id);
            j.put("nombre", this.nombre);
            j.put("contraseña", this.contraseña); // Si deseas no incluir la contraseña por seguridad, omítela.
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return j;
    }

    public static Administrador JsonAObj(JSONObject o) {
        Administrador administrador = null;
        try {
            // Obtener datos heredados
            Persona persona = Persona.JsonAObj(o);

            // Obtener atributos específicos
            int id = o.getInt("id");
            String contraseña = o.getString("contraseña");

            // Crear el objeto Administrador
            administrador = new Administrador(id, persona.getNombre(), contraseña);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return administrador;
    }

}
