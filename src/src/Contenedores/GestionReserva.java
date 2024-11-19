package Contenedores;
import Enums.EstadoHabitacion;
import Reserva.Reserva;
import Reserva.Habitacion;
import java.time.LocalDate;
import java.util.*;

public class GestionReserva {
    private HashMap<String, HashSet<Reserva>> listaReservas;
    private ArrayList<Habitacion> listaHabitaciones;
    /// -----------------------------------------*-----------------------------------------
    public GestionReserva(HashMap<String, HashSet<Reserva>> listaReservas) {
        this.listaReservas = new HashMap<>();
        this.listaHabitaciones=new ArrayList<>();
    }

    public GestionReserva(){
        this.listaReservas = new HashMap<>();
        this.listaHabitaciones=new ArrayList<>();
    }
    /// -----------------------------------------*-----------------------------------------
    /// METODOS PARA INICIALIZAR
    public void inciicalizarLasHabitaciones()
    {
     for(int i =1; i<51;i++)
     {
         listaHabitaciones.add(new Habitacion(i,EstadoHabitacion.DISPONIBLE));
     }
    }

    /// -----------------------------------------*-----------------------------------------
        /// metodo buscar habitaciones disponibles

    public ArrayList<Habitacion> obtenerDisponibles(LocalDate checkIn, LocalDate checkOut){
        ArrayList<Habitacion> disponibles=new ArrayList<>();

        for(Habitacion habitacion : listaHabitaciones){
            if(habitacion.estaDisponible(checkIn, checkOut)){
                disponibles.add(habitacion);
            }
        }

        return disponibles;
    }


        /// metodo para actualizar el estado de las habitaciones

    public void actualizarEstadoHabitaciones(){
        LocalDate hoy = LocalDate.now();

        for(Habitacion habitacion : listaHabitaciones){
            Iterator<Reserva> iterator = habitacion.getListaReservas().iterator();

            while(iterator.hasNext()){
                Reserva reserva =iterator.next();
                if(reserva.getCheckOut().isBefore(hoy)){
                    /// es decir, la reserva ya termino
                    iterator.remove();///por lo tanto la elimina
                }
            }

            /// si la habitacion no tiene reservas activas, se pone como disponible
            if(habitacion.getListaReservas().isEmpty()){
                habitacion.setEstado(EstadoHabitacion.DISPONIBLE);
            }
        }
    }





    /// -----------------------------------------*-----------------------------------------
    /// metodo agregar el dni de un cliente recien registrado al hashMap
    public void agregarDniDeClienteNuevo(String dniCliente)
    {
        listaReservas.put(dniCliente,new HashSet<>());
    }

    /// -----------------------------------------*-----------------------------------------


    public String removeReserva(String dniCliente, Reserva reserva){
        String msj="Error al eliminar la reserva! Intente de nuevo por favor.";

        if(listaReservas.containsKey(dniCliente)){
            HashSet<Reserva> reservasCliente= listaReservas.get(dniCliente);
            reservasCliente.remove(reserva);

            if(reservasCliente.isEmpty()){
                listaReservas.remove(dniCliente);
                 msj="La reserva ha sido eliminada con exito.";
            }
        }

        return msj;
    }



    /// -----------------------------------------*-----------------------------------------
     /// METODOS PARA MOSTRAR

    public String mostrarTodasLasReservas(){
        StringBuilder sb=new StringBuilder();

        /// recorrer hashmap
        for(String dniCliente : listaReservas.keySet()){
            sb.append("[ Reservas del cliente con DNI: ").append(dniCliente).append(" ]\n");

            HashSet<Reserva> reservasCliente=listaReservas.get(dniCliente);
            if(reservasCliente.isEmpty()){
                sb.append(" No tiene reservas.\n");
            }else{
                /// aca se recorre las reservas de ese dni

                for(Reserva reserva : reservasCliente){
                    sb.append(" -").append(reserva.toString()).append("\n");
                }
            }
        }

        /// retornar el SB convertido a String
        return sb.toString();
    }


    public String mostrarHabitaciones()
    {
        String mensaje = "";
        ListIterator<Habitacion> recorredor = listaHabitaciones.listIterator();
        while(recorredor.hasNext())
        {
            Habitacion habitacion = recorredor.next();
            mensaje = mensaje.concat(habitacion.toString()+"\n");

        }
        return mensaje;
    }

    /// -----------------------------------------*-----------------------------------------

}