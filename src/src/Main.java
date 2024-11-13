import Contenedores.GestionReserva;
import Reserva.Reserva;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Reserva reserva=new Reserva();

        System.out.println(reserva.toString());

        Reserva res1=new Reserva("12345123", "2024-01-01", "2024-01-05", 10);
        Reserva res2=new Reserva("89098897", "2024-01-01", "2024-01-05", 11);
        Reserva res3=new Reserva("87463123", "2024-01-01", "2024-01-05", 12);
        Reserva res4=new Reserva("87463123", "2024-01-01", "2024-01-05", 14);

        GestionReserva listaReservas=new GestionReserva();
        listaReservas.addReserva("12345123", res1);
        listaReservas.addReserva("89098897", res2);
        listaReservas.addReserva("87463123", res3);
        listaReservas.addReserva("87463123", res4);
        System.out.println(listaReservas.mostrarTodasLasReservas());

    }
}