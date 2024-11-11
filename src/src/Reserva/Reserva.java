package Reserva;

import Clientes.Cliente;

import java.util.Random;

public class Reserva {

    private int id;
    private String dniCliente;
    private String checkIn;
    private String checkOut;
    private int numeroHabitacionReservada;

    /// -----------------------------------------------------------------------------------------------------------------

    public Reserva(int id, String dniCliente, String checkIn, String checkOut, int numeroHabitacionReservada) {
        this.id = generarId();
        this.dniCliente = dniCliente;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numeroHabitacionReservada = numeroHabitacionReservada;
    }

    public Reserva() {
        this.id=generarId();
    }

    /// -----------------------------------------------------------------------------------------------------------------

    public int getId() {
        return id;
    }

    private int generarId(){
        Random rand= new Random();
        return rand.nextInt(1_000_000);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getNumeroHabitacionReservada() {
        return numeroHabitacionReservada;
    }

    public void setNumeroHabitacionReservada(int numeroHabitacionReservada) {
        this.numeroHabitacionReservada = numeroHabitacionReservada;
    }

    /// -----------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", dniCliente='" + dniCliente + '\'' +
                ", checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", numeroHabitacionReservada=" + numeroHabitacionReservada +
                '}';
    }
}
