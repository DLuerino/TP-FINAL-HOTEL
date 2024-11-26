package Clientes;

import Interfaces.IJSON;
import org.json.JSONException;
import org.json.JSONObject;

public class Domicilio implements IJSON {
    private String calle;
    private int numCalle;
    private boolean departamento;
    /// -----------------------------------------------------------------------------------------------------------------
    public Domicilio(String calle, int numCalle, boolean departamente) {
        this.calle = calle;
        this.numCalle = numCalle;
        this.departamento = departamente;
    }

    public Domicilio() {
    }

    /// -----------------------------------------------------------------------------------------------------------------
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumCalle() {
        return numCalle;
    }

    public void setNumCalle(int numCalle) {
        this.numCalle = numCalle;
    }

    public boolean isDepartamento() {
        return departamento;
    }

    public void setDepartamente(boolean departamente) {
        this.departamento = departamento;
    }

    /// -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "\n Calle:         " + String.format("%-35s", this.calle) +
                "\n Número Calle:  " + String.format("%-35d", this.numCalle) +
                "\n Departamento:   " + String.format("%-35s", this.departamento ? "Sí" : "No");
    }

    /// -----------------------------------------------------------------------------------------------------------------

    @Override
    public JSONObject toJSON() {
        JSONObject j = new JSONObject();
        try {
            j.put("calle", this.calle);
            j.put("numCalle", this.numCalle);
            j.put("departamento", this.departamento);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return j;
    }

    public static Domicilio fromJSON(JSONObject obj) {
        Domicilio domicilio = new Domicilio();
        try {
            domicilio.setCalle(obj.getString("calle"));
            domicilio.setNumCalle(obj.getInt("numCalle"));
            domicilio.setDepartamente(obj.getBoolean("departamento"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return domicilio;
    }

}
