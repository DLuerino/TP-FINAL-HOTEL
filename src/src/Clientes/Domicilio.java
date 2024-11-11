package Clientes;

public class Domicilio {
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

}
