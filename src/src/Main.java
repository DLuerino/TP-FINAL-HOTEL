import Contenedores.GestionReserva;
import Enums.EstadoHabitacion;
import Reserva.Reserva;
import Reserva.Habitacion;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Reserva reserva=new Reserva();

        System.out.println(reserva.toString());

        Reserva res1=new Reserva("12345123", "2024-01-01", "2024-01-05");
        Reserva res2=new Reserva("89098897", "2024-01-01", "2024-01-05");
        Reserva res3=new Reserva("87463123", "2024-01-01", "2024-01-05");
        Reserva res4=new Reserva("87463123", "2024-01-01", "2024-01-05");

        GestionReserva listaReservas=new GestionReserva();

        for(int i=1; i<4; i++){
            listaReservas.addHabitacion(new Habitacion(i, EstadoHabitacion.DISPONIBLE));
        }

        listaReservas.addReserva(res1);
        listaReservas.addReserva(res2);
        listaReservas.addReserva(res3);
        listaReservas.addReserva(res4);

        System.out.println(listaReservas.getListaHabitaciones().toString());


       /* Reserva res5=new Reserva("76321672", "2024-01-06", "2024-01-8");
        listaReservas.addReserva(res5);

        Reserva res6=new Reserva("76321672", "2024-01-06", "2024-01-8");
        listaReservas.addReserva(res6);
        System.out.println(listaReservas.mostrarTodasLasReservas()); */






    }
}