package Reserva;

import Clientes.Cliente;

public class Reserva {

    private Cliente cliente;
    private String fechaCheckin;
    private String fechaCheckout;
    private Habitacion habitacion;

    public Reserva(Cliente cliente, String fechaCheckin, String fechaCheckout, Habitacion habitacion) {
        this.cliente = cliente;
        this.fechaCheckin = fechaCheckin;
        this.fechaCheckout = fechaCheckout;
        this.habitacion = habitacion;
    }

    public Reserva() {}

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFechaCheckout() {
        return fechaCheckout;
    }

    public void setFechaCheckout(String fechaCheckout) {
        this.fechaCheckout = fechaCheckout;
    }

    public String getFechaCheckin() {
        return fechaCheckin;
    }

    public void setFechaCheckin(String fechaCheckin) {
        this.fechaCheckin = fechaCheckin;
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
                ", fechaCheck='" + fechaCheckin + '\'' +
                ", fechaCheck='" + fechaCheckout + '\'' +
                ", habitacion=" + habitacion +
                '}';
    }



}
