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
    public JSONObject ObjAJson() {
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

    public static Domicilio JsonAObj(JSONObject o) {
        Domicilio domicilio = null;
        try {
            String calle = o.getString("calle");
            int numCalle = o.getInt("numCalle");
            boolean departamento = o.getBoolean("departamento");

            domicilio = new Domicilio(calle, numCalle, departamento);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return domicilio;
    }

}
