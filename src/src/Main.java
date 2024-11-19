import Clientes.Cliente;
import Contenedores.GestionGeneral;
import Contenedores.GestionReserva;
import Enums.EstadoHabitacion;
import MENU.Menu;
import Reserva.Reserva;
import Reserva.Habitacion;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

       /* Reserva reserva=new Reserva();

        Cliente cliente1 = new Cliente();
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
        System.out.println(""+gestionReserva.mostrarTodasLasReservas());


        GestionReserva gestionReserva1 = new GestionReserva();
        gestionReserva1.inciicalizarLasHabitaciones();
        System.out.println(gestionReserva1.mostrarHabitaciones()); */

        Menu menu = new Menu();
        menu.ejecutarMenu();

    }
}