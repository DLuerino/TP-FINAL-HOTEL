package Reserva;

import Excepciones.ErrorAlIngresarException;
import Excepciones.ErrorFechaException;
import Interfaces.iJSON;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;

public class Reserva implements iJSON {

    private int id;
    private String dniCliente;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int numeroHabitacionReservada;
    private static HashSet<Integer> idGenerados=new HashSet<>();

    /// -----------------------------------------*-----------------------------------------

    public Reserva(String dniCliente, LocalDate checkIn, LocalDate checkOut, int numeroHabitacionReservada) {
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

    public Reserva(String dniCliente, LocalDate checkIn, LocalDate checkOut) {
        this.id=generarId();
        this.dniCliente=dniCliente;
        this.checkIn=checkIn;
        this.checkOut=checkOut;
    }

    /// -----------------------------------------*-----------------------------------------

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

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public int getNumeroHabitacionReservada() {
        return numeroHabitacionReservada;
    }

    public void setNumeroHabitacionReservada(int numeroHabitacionReservada) {
        this.numeroHabitacionReservada = numeroHabitacionReservada;
    }

    /// -----------------------------------------*-----------------------------------------

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

    /// -----------------------------------------*-----------------------------------------

    /// metodo para verificar reserva
    public void verificarDatosReserva() throws ErrorAlIngresarException
    {
        if(dniCliente.isEmpty())
        {
            throw new ErrorAlIngresarException("Hay campos sin completar correctamente..");
        }
        if(checkIn.isBefore(LocalDate.now()))
        {
            throw new ErrorFechaException("La fecha checkIn no puede ser menor a la fecha actual... ");
        }
        if(checkOut.isBefore(LocalDate.now()))
        {
            throw new ErrorFechaException("La fecha checkOut no puede ser menor a la fecha actual");
        }
        if(checkIn.isAfter(checkOut)) {
            throw new ErrorFechaException("La fecha checkIn no puede ser mayor a la del checkOut");
        }
        if (checkOut.isBefore(checkIn))
        {
            throw new ErrorFechaException("La fecha checkOut no puede ser menor a la del checkIn");
        }
    }

    /// -----------------------------------------------------------------------------------------------------------------

    @Override
    public JSONObject ObjAJson() {
        return null;
    }



}
