package CLIENTES;

public class Domicilio {
    private String calle;
    private int numCalle;
    private boolean departamente;

    public Domicilio(String calle, int numCalle, boolean departamente) {
        this.calle = calle;
        this.numCalle = numCalle;
        this.departamente = departamente;
    }

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

    public boolean isDepartamente() {
        return departamente;
    }

    public void setDepartamente(boolean departamente) {
        this.departamente = departamente;
    }
}
