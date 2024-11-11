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
    // ------------------------------------------------------------------------------------------------------------

    public HashMap<String, HashSet<Reserva>> getListaReservas() {
        return listaReservas;
    }
}