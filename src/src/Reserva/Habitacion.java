package Reserva;

import Enums.EstadoHabitacion;
import Excepciones.ReservaYaRegistradaException;
import Interfaces.IJSON;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;

public class Habitacion implements IJSON {

    private int numeroHabitacion;
    private EstadoHabitacion estado;
    private ArrayList<Reserva> listaReservas;
    /// -----------------------------------------------------------------------------------------------------------------

    public Habitacion(int numeroHabitacion, EstadoHabitacion estado) {
        this.numeroHabitacion = numeroHabitacion;
        this.estado = estado;
        this.listaReservas = new ArrayList<>();
    }

    public Habitacion() {
        this.listaReservas=new ArrayList<>();
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
    public JSONObject toJSON() {
        JSONObject obj=new JSONObject();

        try{
            /// convertir atributos

            obj.put("NumeroDeHabitacion", numeroHabitacion);
            obj.put("Estado", estado.name());///.name convierte el enum en string

            /// convertir lista de reservas
            JSONArray reservasArray=new JSONArray();

            for(Reserva reserva : listaReservas){
                reservasArray.put(reserva.toJSON());
            }

            obj.put("ListaDeReservas", reservasArray);
        }catch (JSONException e){
            e.printStackTrace();
        }

        return obj;
    }

    public static Habitacion fromJSON(JSONObject obj){
        /// Reconstruir datos
        Habitacion habitacion=new Habitacion();

        try{
            habitacion.numeroHabitacion=obj.getInt("NumeroDeHabitacion");
            habitacion.estado=EstadoHabitacion.valueOf(obj.getString("Estado"));

            ///reconstruir reservas
            habitacion.listaReservas=new ArrayList<>();
            JSONArray reservasArray=obj.getJSONArray("ListaDeReservas");

            for(int i=0; i<reservasArray.length(); i++){
                JSONObject reservaJSON=reservasArray.getJSONObject(i);

                Reserva reserva=new Reserva();///Reserva vacia
                reserva.fromJSON(reservaJSON);///Reconstruir reserva
                habitacion.listaReservas.add(reserva);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return habitacion;
    }
}
