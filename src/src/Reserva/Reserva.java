package Reserva;

import Clientes.Cliente;

import java.util.HashSet;
import java.util.Random;

public class Reserva {

    private int id;
    private String dniCliente;
    private String checkIn;
    private String checkOut;
    private int numeroHabitacionReservada;
    private static HashSet<Integer> idGenerados=new HashSet<>();

    /// -----------------------------------------------------------------------------------------------------------------

    public Reserva( String dniCliente, String checkIn, String checkOut, int numeroHabitacionReservada) {
        this.id = generarId();
        this.dniCliente = dniCliente;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numeroHabitacionReservada = numeroHabitacionReservada;
    }

    public Reserva() {
        this.id=generarId();
    }

    public Reserva(String nueva){
        this.id=generarId();
    }

    /// -----------------------------------------------------------------------------------------------------------------

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
