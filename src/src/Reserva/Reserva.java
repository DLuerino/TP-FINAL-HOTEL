package Reserva;

import Clientes.Cliente;

public class Reserva {

    private Cliente cliente;
    private String fechaCheckIn;
    private String fechaCheckOut;
    private Habitacion habitacion;

    public Reserva(Cliente cliente, String fechaCheck, String fechaCheck1, Habitacion habitacion) {
        this.cliente = cliente;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.habitacion = habitacion;
    }

    public Reserva() {}

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(String fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }

    public String getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(String fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
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
                ", fechaCheckIn='" + fechaCheckIn + '\'' +
                ", fechaCheckOut='" + fechaCheckOut + '\'' +
                ", habitacion=" + habitacion +
                '}';
    }
}
