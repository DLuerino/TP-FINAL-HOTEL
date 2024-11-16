package Contenedores;

import Enums.EstadoHabitacion;
import Excepciones.NoDisponibleException;
import Reserva.Reserva;
import Reserva.Habitacion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GestionReserva {
    private HashMap<String, HashSet<Reserva>> listaReservas;
    private ArrayList<Habitacion> listaHabitaciones;
    // ------------------------------------------------------------------------------------------------------------
    public GestionReserva(HashMap<String, HashSet<Reserva>> listaReservas) {
        this.listaReservas = new HashMap<>();
        this.listaHabitaciones=new ArrayList<>();
    }

    public GestionReserva(){
        this.listaReservas = new HashMap<>();
        this.listaHabitaciones=new ArrayList<>();
    }
    // ------------------------------------------------------------------------------------------------------------

    public HashMap<String, HashSet<Reserva>> getListaReservas() {
        return listaReservas;
    }


    public ArrayList<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }


    public void addHabitacion(Habitacion habitacion){
        listaHabitaciones.add(habitacion);
    }

    public void addReserva(Reserva reserva) throws NoDisponibleException {
        /// verificar disponibilidad
        ArrayList<Habitacion> habDisponibles=new ArrayList<>();

        for(Habitacion habitacion : listaHabitaciones){


        }

        /// DUDA: COMO MANEJAR LA DISPONIBILIDAD POR FECHAS, TENIENDO EN CUENTA QUE DESDE EL CHECK-IN HASTA EL CHECK-OUT ESTA OCUPADA, PERO DESPUES DE ESE PLAZO YA DEBERIA ESTAR DISPONIBLE
        /// PREGUNTR ESO;

//        for(Reserva res : listaReservas){
//            if (reserva.getCheckIn().compareTo(reserva.getCheckOut()) < 0 && reserva.getCheckOut().compareTo(reserva.getCheckIn()) > 0){
//                habDisponibles.add(new Habitacion(reserva.getNumeroHabitacionReservada(), EstadoHabitacion.DISPONIBLE));
//            }
//        }

        if(habDisponibles.isEmpty()){
            throw new NoDisponibleException("No hay habitaciones disponibles para estas fechas!");
        }

        Habitacion seleccionada= habDisponibles.get(0);
        reserva.setNumeroHabitacionReservada(seleccionada.getNumeroHabitacion());

        ///verifica si el cliente ya tiene alguna reserva
        if(!listaReservas.containsKey(reserva.getDniCliente())){
            ///por que no existe crea uno
            listaReservas.put(reserva.getDniCliente(), new HashSet<>());
        }

        ///agrega la reserva al conjunto del cliente
        listaReservas.get(reserva.getDniCliente()).add(reserva);

        seleccionada.setEstado(EstadoHabitacion.OCUPADA);

    }

    public HashSet<Reserva> getReservasXcliente(String dniCliente){
        return listaReservas.getOrDefault(dniCliente, new HashSet<>());
    }

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
}