package Reserva;

import ENUMS.EstadoHabitacion;

public class Habitacion {

    private int numeroHabitacion;
    private boolean disponibilidad;
    private EstadoHabitacion estado;

    public Habitacion(int numeroHabitacion, boolean disponibilidad, EstadoHabitacion estado) {
        this.numeroHabitacion = numeroHabitacion;
        this.disponibilidad = disponibilidad;
        this.estado = estado;
    }

    public Habitacion() {}

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public EstadoHabitacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoHabitacion estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "numeroHabitacion=" + numeroHabitacion +
                ", disponibilidad=" + disponibilidad +
                ", estado=" + estado +
                '}';
    }

}
