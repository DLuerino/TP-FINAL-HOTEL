package Reserva;

import Enums.EstadoHabitacion;

import java.util.ArrayList;

public class Habitacion {

    private int numeroHabitacion;
    private EstadoHabitacion estado;
    private ArrayList<Reserva> listaReservas;
    /// -----------------------------------------------------------------------------------------------------------------

    public Habitacion(int numeroHabitacion, EstadoHabitacion estado) {
        this.numeroHabitacion = numeroHabitacion;
        this.estado = estado;
        this.listaReservas=new ArrayList<>();
    }

    public Habitacion() {this.listaReservas=new ArrayList<>();}
    /// -----------------------------------------------------------------------------------------------------------------

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public EstadoHabitacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoHabitacion estado) {
        this.estado = estado;
    }

    ///Verificar si la habitación está disponible para un rango de fechas
    public boolean estaDisponible(String checkIn, String checkOut) {
        if (estado != EstadoHabitacion.DISPONIBLE) {
            return false;  /// Si la habitación no está disponible, retornamos false
        }

        for (Reserva reserva : listaReservas) {
            if (reserva.getCheckIn().compareTo(checkOut) < 0 && reserva.getCheckOut().compareTo(checkIn) > 0) {
                return false;  /// La habitación ya está ocupada en el rango de fechas
            }
        }

        return true;  /// La habitación está disponible
    }

    /// -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Habitacion{" +
                "numeroHabitacion=" + numeroHabitacion +
                ", disponibilidad=" +
                ", estado=" + estado +
                '}';
    }

}
