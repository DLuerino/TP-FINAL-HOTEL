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
        Menu menu=new Menu();
        menu.ejecutarMenu();

    }
}