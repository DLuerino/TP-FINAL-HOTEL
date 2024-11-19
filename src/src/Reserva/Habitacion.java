package Reserva;

import Enums.EstadoHabitacion;
import Excepciones.ReservaYaRegistradaException;
import Interfaces.metodoJson;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Habitacion implements metodoJson {

    private int numeroHabitacion;
    private EstadoHabitacion estado;
    private ArrayList<Reserva> listaReservas;
    /// -----------------------------------------------------------------------------------------------------------------

    public Habitacion(int numeroHabitacion, EstadoHabitacion estado) {
        this.numeroHabitacion = numeroHabitacion;
        this.estado = estado;
        this.listaReservas = new ArrayList<>();
    }

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

    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }

    /// -----------------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Habitacion{" +
                "numeroHabitacion=" + numeroHabitacion +
                ", estado=" + estado +
                ", listaReservas=" + listaReservas +
                '}';
    }

    /// ---------------------------------------------------------------------------------------------------------------
    public void agregarReserva(Reserva reservita) throws ReservaYaRegistradaException
    {
        if(listaReservas.contains(reservita))
        {
         throw new ReservaYaRegistradaException("La reserva ya se encuentra registrada en el sistema");
        }
    }
    /// ---------------------------------------------------------------------------------------------------------------
    public Boolean estaDisponible(LocalDate checkIn, LocalDate checkOut) {
        for (Reserva reserva : listaReservas) {
            // Verificar solapamiento
            if (!(checkOut.isBefore(reserva.getCheckIn()) || checkIn.isAfter(reserva.getCheckOut()))) {
                return false;
            }
        }
        return true; // No hay solapamientos, está disponible
    }

    /// -----------------------------------------------------------------------------------------------------------------

    @Override
    public JSONObject ObjAJson() {
        return null;
    }



}
