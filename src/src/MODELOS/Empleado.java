package MODELOS;

import Enums.TipoEmpleado;
import Excepciones.DniIngresoException;
import Excepciones.ErrorAlIngresarException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

public class Empleado extends Persona{

    private int id;
    private String contraseña;
    private TipoEmpleado rol;
    private static HashSet<Integer> idGenerados=new HashSet<>();


    /// constructor recepcionista
    public Empleado(String nombre, String apellido, String gmail, String contraseña, TipoEmpleado rol) {
        super(nombre, apellido, gmail);
        this.id =generarId();
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public Empleado(){
    }

    public Empleado(int id)
    {
        this.id = id;
    }

    public Empleado(String nombre, String apellido, String gmail, int id, String contraseña, TipoEmpleado rol) {
        super(nombre, apellido, gmail);
        this.id = id;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    /// ---------------------------------------------------------------------------------------------------------------

    public int getId() {
        return id;
    }

    private static int generarId(){
        Random rand= new Random();
        int id;

        do{
            id=rand.nextInt(1_000_000);
        }while(idGenerados.contains(id)); ///si el id ya esta generado, lo que hace es generar uno nuevo

        idGenerados.add(id); ///agrega el id al conjunto

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

    public void verificarEmpleado() throws ErrorAlIngresarException{

        if(this.nombre.isEmpty() || this.apellido.isEmpty() || this.contraseña.isEmpty() || this.rol==null || this.contraseña==null || this.gmail.isEmpty()){
            throw new ErrorAlIngresarException("\n Complete el espacio vacio. ");
        }

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
        return "\n╔═════════════════════════════════════════════╗" +
                "\n║                  EMPLEADO                   ║" +
                "\n╠═════════════════════════════════════════════╣" +
                "\n ID:             " + String.format("%-30s", this.id) +
                ""+super.toString()+
                "\n Contraseña:     " + String.format("%-30s", this.contraseña)+
                "\n╠═════════════════════════════════════════════╣";
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
            empleado.setNombre(o.getString("nombre"));
            empleado.setApellido(o.getString("apellido"));
            empleado.setGmail(o.getString("correoElectronico"));
            empleado.setId(o.getInt("id"));
            empleado.setContraseña(o.getString("contraseña"));
            empleado.setRol(TipoEmpleado.valueOf(o.getString("rol")));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return empleado;
    }


}
