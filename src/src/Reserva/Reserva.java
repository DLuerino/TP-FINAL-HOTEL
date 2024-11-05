package Reserva;

public class Reserva {

    private Cliente cliente;
    private String fechaCheck-in;
    private String fechaCheck-out;
    private Habitacion habitacion;

    public Reserva(Cliente cliente, String fechaCheck, String fechaCheck1, Habitacion habitacion) {
        this.cliente = cliente;
        this.fechaCheck = fechaCheck;
        this.fechaCheck = fechaCheck1;
        this.habitacion = habitacion;
    }

    public Reserva() {}

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFechaCheck() {
        return fechaCheck;
    }

    public void setFechaCheck(String fechaCheck) {
        this.fechaCheck = fechaCheck;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "cliente=" + cliente +
                ", fechaCheck='" + fechaCheck + '\'' +
                ", fechaCheck='" + fechaCheck + '\'' +
                ", habitacion=" + habitacion +
                '}';
    }



}
