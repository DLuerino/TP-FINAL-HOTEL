import Clientes.Cliente;
import Contenedores.GestionGeneral;
import Contenedores.GestionReserva;
import Enums.EstadoHabitacion;
import Reserva.Reserva;
import Reserva.Habitacion;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Reserva reserva=new Reserva();

        System.out.println(reserva.toString());

       /* Reserva res1=new Reserva("12345123", "2024-01-01", "2024-01-05");
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

        System.out.println(listaReservas.getListaHabitaciones().toString()); */


       /* Reserva res5=new Reserva("76321672", "2024-01-06", "2024-01-8");
        listaReservas.addReserva(res5);

        Reserva res6=new Reserva("76321672", "2024-01-06", "2024-01-8");
        listaReservas.addReserva(res6);
        System.out.println(listaReservas.mostrarTodasLasReservas()); */

        /*Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        Cliente cliente3 = new Cliente();
        cliente1.setDni("1111");
        cliente2.setDni("2222");
        cliente3.setDni("3333");


        GestionGeneral<Cliente> listaClientes = new GestionGeneral<>();
        GestionReserva gestionReserva = new GestionReserva();

        listaClientes.agregarObjeto(cliente1);
        gestionReserva.agregarDniDeClienteNuevo(cliente1.getDni());
        listaClientes.agregarObjeto(cliente1);
        gestionReserva.agregarDniDeClienteNuevo(cliente2.getDni());
        listaClientes.agregarObjeto(cliente3);
        gestionReserva.agregarDniDeClienteNuevo(cliente3.getDni());

        System.out.println(""+listaClientes.getListaRegistros());
        System.out.println(""+gestionReserva.mostrarTodasLasReservas()); */


        GestionReserva gestionReserva = new GestionReserva();
        gestionReserva.inciicalizarLasHabitaciones();
        System.out.println(gestionReserva.mostrarHabitaciones());

    }
}