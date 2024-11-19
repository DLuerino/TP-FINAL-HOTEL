package MODELOS;

import Excepciones.ErrorAlIngresarException;
import Excepciones.IdNegativaException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.Random;

public class Recepcionista extends Persona {


    private int id;
    private String contraseña;

    public Recepcionista(String nombre, String apellido, String gmail, int id, String contraseña) {
        super(nombre, apellido, gmail);
        this.id = id;
        this.contraseña = contraseña;
    }

    public Recepcionista() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recepcionista that = (Recepcionista) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Recepcionista{"+ super.toString() +
                "id=" + id +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }

    private static int generarId(){
        Random random = new Random();
        return random.nextInt(1_000_000) + 1;
    }

    public void verificarRecepcionista() throws ErrorAlIngresarException {
        if(this.nombre.isEmpty() || this.apellido.isEmpty() || this.contraseña.isEmpty() || this.gmail.isEmpty()){
            throw new ErrorAlIngresarException("\n Complete el espacio vacio. ");
        }
        if(this.id<0)
        {
            throw new IdNegativaException("La id ingresada no puede ser negativa");
        }
    }

    /// -----------------------------------------------------------------------------------------------------------------

    @Override
    public JSONObject toJSON() {
        JSONObject j = super.toJSON(); // Llama al método de la clase base Persona
        try {
            j.put("id", this.id);
            j.put("contraseña", this.contraseña); // Nota: Considera la seguridad al manejar contraseñas.
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return j;
    }

    public static Recepcionista fromJSON(JSONObject o) {
        Recepcionista recepcionista = new Recepcionista();
        try {
            int id = o.getInt("id");
            String contraseña = o.getString("contraseña");
            // Crear el objeto Recepcionista
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recepcionista;
    }
}
