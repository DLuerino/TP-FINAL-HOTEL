package Contenedores;

import Reserva.Reserva;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GestionReserva {
    private HashMap<String, HashSet<Reserva>> listaReservas;
    // ------------------------------------------------------------------------------------------------------------
    public GestionReserva(HashMap<String, HashSet<Reserva>> listaReservas) {
        this.listaReservas = new HashMap<>();
    }

    public GestionReserva(){
        this.listaReservas = new HashMap<>();
    }
    // ------------------------------------------------------------------------------------------------------------

    public HashMap<String, HashSet<Reserva>> getListaReservas() {
        return listaReservas;
    }

    public void addReserva(String dniCliente, Reserva reserva){
        ///verifica si el cliente ya tiene alguna reserva
        if(!listaReservas.containsKey(dniCliente)){
            ///por que no existe crea uno
            listaReservas.put(dniCliente, new HashSet<>());
        }

        ///agrega la reserva al conjunto del cliente
        listaReservas.get(dniCliente).add(reserva);
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