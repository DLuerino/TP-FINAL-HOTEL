package MENU;

import Clientes.Cliente;
import Clientes.Domicilio;
import Contenedores.GestionGeneral;
import Contenedores.GestionReserva;
import Enums.TipoEmpleado;
import Excepciones.*;
import JSONUtiles.JSONUtiles;
import MODELOS.Empleado;
import Reserva.Reserva;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import Reserva.Habitacion;

import java.awt.*;
import java.io.File;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Menu {

    /// MENU DEL PROGRAMA

    /// -------------------------------------------------*-------------------------------------------------
    /// CONSTRUCTOR
    public Menu() {
    }
    ///-------------------------------------------------*-------------------------------------------------

    /// METODO PARA EJECUTAR EL MENU DEL PROGRAMA
    public void ejecutarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        /// GESTIONES QUE UTILIZAREMOS
        GestionGeneral<Cliente> gestionClientes = new GestionGeneral<>();
        GestionGeneral<Empleado> gestionEmpleados = new GestionGeneral<>();
        GestionReserva gestionReservas = new GestionReserva();
        /// INICIALIZAMOS LAS GESTIONES:

        /// STRINGS PARA LOS ARCHIVOS
        String clientesNombreArchivo = "Clientes.json";
        String reservasNombreArchivo = "Reservas.json";
        String empleadosNombreArchivo = "Empleados.json";

        /// ARCHIVOS QUE UTILIZAREMOS
        File fileClientes = new File(clientesNombreArchivo);
        File fileReservas = new File(reservasNombreArchivo);
        File fileEmpleados = new File(empleadosNombreArchivo);


        /// VERIFICACIONES PARA SABER SI LOS ARCHIVOS EXISTEN O NO (SI NO EXISTEN LOS CREAMOS E INICIALIZAMOS LOS DATOS NECESARIOS PARA EL SISTEMA)

        /// ARCHIVO RESERVA
        if (!fileReservas.exists()) {
            gestionReservas.inciicalizarLasHabitaciones();
            JSONUtiles.grabar(new JSONArray().put(gestionReservas.toJSON()), reservasNombreArchivo);
        }

        /// ARCHIVO CLIENTES
        if (!fileClientes.exists()) {
           serializarGestionClientes(gestionClientes,clientesNombreArchivo);
        }

        /// ARCHIVO EMPLEADOS
        if (!fileEmpleados.exists()) {
            GestionGeneral<Empleado> gestionEmpleadoAux = new GestionGeneral<>();
            try {

                gestionEmpleadoAux.agregarObjeto(new Empleado("Facundo", "Galeano", "galeanofacundo752@gmail.com", 0 ,"utntpfinal", TipoEmpleado.ADMINISTRADOR));
                gestionEmpleadoAux.agregarObjeto(new Empleado("Diego", "Luerino", "lueriodiego@gmail.com", 1, "utntpfinal", TipoEmpleado.ADMINISTRADOR));
                gestionEmpleadoAux.agregarObjeto(new Empleado("Marco", "Olivero", "marcoolivero@gmail.com", 2, "utntpfinal", TipoEmpleado.ADMINISTRADOR));
            } catch (ObjetoYaRegistradoException e) {
                System.out.println("Error debido a que: " + e.getMessage());
            }
            serializarGestionEmpleados(gestionEmpleadoAux,empleadosNombreArchivo);
        }

        /// AHORA REALIZAMOS EL CODIGO QUE LEE LOS ARCHIVOS DEL PROGRAMA Y GUARDA LOS DATOS EN LAS VARIABLES GESTIONES ASI TRABAJAMOS CON ELLAS Y SUS DATOS
        /// LEEMOS Y GUARDAMOS LA GESTION RESERVAS
        try {
            /// LEEMOS Y GUARDAMOS LA GESTION RESERVAS
            JSONArray auxReservas = new JSONArray(JSONUtiles.leer(reservasNombreArchivo));
            gestionReservas = GestionReserva.fromJSON(auxReservas.getJSONObject(0));

            /// AHORA DEBERIAMOS LEER Y GUARDAR LA GESTION GENERAL (GENERICA) QUE CONTIENE LOS CLIENTES

            JSONArray jsonArray1 = new JSONArray(JSONUtiles.leer(clientesNombreArchivo));
            JSONObject registroCliente = jsonArray1.getJSONObject(0); // Primer objeto del JSON
            JSONArray listaClientes = registroCliente.getJSONArray("listaClientes");
            for (int c = 0; c < listaClientes.length(); c++) {
                JSONObject jsonCliente = listaClientes.getJSONObject(c);
                Cliente cliente = Cliente.fromJSON(jsonCliente); // Deserializa el cliente
                gestionClientes.agregarObjeto(cliente); // Agrega al gestor
            }


            /// LEEMOS Y GUARDAMOS LA GESTION GENERAL (GENERICA) QUE CONTIENE EMPLEADOS
            JSONArray jsonArray2 = new JSONArray(JSONUtiles.leer(empleadosNombreArchivo));
            JSONObject registroEmpleados = jsonArray2.getJSONObject(0); // Primer objeto del JSON
            JSONArray listaEmpleados = registroEmpleados.getJSONArray("listaEmpleados");
            for (int i = 0; i < listaEmpleados.length(); i++) {
                JSONObject jsonEmpleado = listaEmpleados.getJSONObject(i);
                Empleado empleado = Empleado.fromJSON(jsonEmpleado);
                // Deserializa el empleado
                gestionEmpleados.agregarObjeto(empleado); // Agrega al gestor
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /// --------------------------------------------------*--------------------------------------------------------
        Boolean salirAux = false;
        Empleado empleadoAux = new Empleado();
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║ Bienvenido al sistema del hotel UTN...           ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        while (!salirAux) {
            System.out.println("\n\n╔════════════════════════════════════════════════╗");
            System.out.println("║ Para ingresar al sistema, ingrese su ID.       ║");
            System.out.println("║ Luego le pediremos su contraseña.              ║");
            System.out.println("║ No la comparta con nadie. Muchas gracias.      ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.println("Ingrese su id: ");
            try {
            int idIngresada = sc.nextInt();
            empleadoAux = gestionEmpleados.buscarObjetoYretornarlo(new Empleado(idIngresada));

                if (empleadoAux == null) {
                    throw new ObjetoNoRegistradoException("El id ingresado no tiene relacion con ningun objeto registrado");
                } else {
                    System.out.println("╔═══════════════════════════════════════════════╗");
                    System.out.println("║ Bienvenido " + empleadoAux.getNombre() + "!!!!!                       ║");
                    System.out.println("╚═══════════════════════════════════════════════╝");
                    System.out.println("Ingrese su contraseña:");
                    String contraseñaAux = sc.next();
                    if (empleadoAux.getContraseña().equals(contraseñaAux)) {
                        System.out.println("Contraseña correcta, redireccionandolo al sistema..");
                        salirAux=true;
                    } else {
                        throw new ContraseñasNoCoincideException("La contraseña ingresada es incorrecta.");
                    }

                }
            } catch (ObjetoNoRegistradoException | ContraseñasNoCoincideException | InputMismatchException e) {
                System.out.println("╔════════════════════════════════════════════╗");
                System.out.println("║ Error: " + e.getMessage() + "                     ║");
                System.out.println("╚════════════════════════════════════════════╝");
                sc.nextLine();
            }
        }

        /// BOOLEANO PARA EL SWITCH
        Boolean salirSwitch = false;
        while (!salirSwitch)
        {
            /// METODO SWITCH PARA ADMINISTRADOR

            gestionReservas.realizarCheckIns();
            gestionReservas.realizarCheckOuts();
            System.out.println("\n╔══════════════════════════════════════════════════════╗");
            System.out.println("║                    MENÚ PRINCIPAL                    ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.println("║ 1. Registrar una reserva.                            ║");
            System.out.println("║ 2. Registrar un cliente.                             ║");
            System.out.println("║ 3. Ver historial de reservas de un cliente.          ║");
            System.out.println("║ 4. Ver todos los clientes.                           ║");
            System.out.println("║ 5. Ver todas las reservas.                           ║");
            System.out.println("║ 6. Ver todas las habitaciones.                       ║");
            System.out.println("║ 7. Eliminar una reserva.                             ║");
            System.out.println("║ 8. Eliminar un cliente.                              ║");
            System.out.println("║ 9. Registrar un nuevo empleado (ADMINISTRADOR).      ║");
            System.out.println("║ 10. Despedir un empleado (ADMINISTRADOR).            ║");
            System.out.println("║ 11. Ver empleados registrados (ADMINISTRADOR).       ║");
            System.out.println("║ 12. Salir del programa.                              ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");

            int op = 0;
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("\n╔═════════════════════════════════╗");
                    System.out.println("║ Opción 1: Registrar una reserva ║");
                    System.out.println("╚═════════════════════════════════╝");
                    agregarReserva(gestionReservas,sc);
                    System.out.println("Ingrese cualquier tecla para continuar: ");
                    sc.next();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    break;
                case 2:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("\n╔════════════════════════════════╗");
                    System.out.println("║ Opción 2: Registrar un cliente ║");
                    System.out.println("╚════════════════════════════════╝");
                    agregarCliente(gestionClientes,gestionReservas,sc);
                    System.out.println("Ingrese cualquier tecla para continuar: ");
                    sc.next();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    break;
                case 3:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("\n╔════════════════════════════════════════╗");
                    System.out.println("║ Opción 3: Ver historial de reservas    ║");
                    System.out.println("╚════════════════════════════════════════╝");
                    mostrarHistorial(gestionReservas, sc);
                    System.out.println("Ingrese cualquier tecla para continuar: ");
                    sc.next();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    break;
                case 4:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("\n╔═════════════════════════════════╗");
                    System.out.println("║ Opción 4: Ver todos los clientes ║");
                    System.out.println("╚════════════════════════════════╝");
                    mostrarClientes(gestionClientes);
                    System.out.println("Ingrese cualquier tecla para continuar: ");
                    sc.next();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    break;
                case 5:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("\n╔══════════════════════════╗");
                    System.out.println("║ Opción 5: Ver reservas   ║");
                    System.out.println("╚══════════════════════════╝");
                    mostrarReservas(gestionReservas);
                    System.out.println("Ingrese cualquier tecla para continuar: ");
                    sc.next();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    break;
                case 6:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("\n╔══════════════════════════╗");
                    System.out.println("║ Opción 6: Ver habitaciones   ║");
                    System.out.println("╚══════════════════════════╝");
                    mostrarHabitaciones(gestionReservas);
                    System.out.println("Ingrese cualquier tecla para continuar: ");
                    sc.next();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    break;
                case 7:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("\n╔═══════════════════════════════╗");
                    System.out.println("║ Opción 7: Eliminar una reserva   ║");
                    System.out.println("╚════════════════════════════════╝");
                    eliminarReserva(gestionReservas,sc,gestionClientes);
                    System.out.println("Ingrese cualquier tecla para continuar: ");
                    sc.next();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    break;
                case 8:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("\n╔═════════════════════════════╗");
                    System.out.println("║ Opción 8: Eliminar un cliente  ║");
                    System.out.println("╚══════════════════════════════╝");
                    eliminarcliente(gestionClientes,gestionReservas,sc);
                    System.out.println("Ingrese cualquier tecla para continuar: ");
                    sc.next();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    break;
                case 9:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("\n╔═══════════════════════════════════════╗");
                    System.out.println("║ Opción 9: Registrar un nuevo empleado ║");
                    System.out.println("╚═══════════════════════════════════════╝");
                    if(empleadoAux.getRol().equals(TipoEmpleado.RECEPCIONISTA)){
                        System.out.println("\nNO puedes acceder a esto por que no tienes permisos de administrador. ");
                    }else{
                        registrarEmpleado(gestionEmpleados, sc);
                    }
                    System.out.println("Ingrese cualquier tecla para continuar: ");
                    sc.next();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    break;
                case 10:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("\n╔═════════════════════════════════╗");
                    System.out.println("║ Opción 10: Despedir un empleado ║");
                    System.out.println("╚═════════════════════════════════╝");
                    if(empleadoAux.getRol().equals(TipoEmpleado.RECEPCIONISTA)){
                        System.out.println("\nNO puedes acceder a esto por que no tienes permisos de administrador. ");
                    }else{
                        despedirEmpleado(gestionEmpleados, sc);
                    }
                    System.out.println("Ingrese cualquier tecla para continuar: ");
                    sc.next();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    break;
                case 11:
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("\n╔══════════════════════════════════════╗");
                    System.out.println("║ Opción 11: Ver empleados registrados ║");
                    System.out.println("╚══════════════════════════════════════╝");
                    if(empleadoAux.getRol().equals(TipoEmpleado.RECEPCIONISTA)){
                        System.out.println("\nNO puedes acceder a esto por que no tienes permisos de administrador. ");
                    }else{
                        mostrarEmpleadosRegistrados(gestionEmpleados);
                    }
                    System.out.println("Ingrese cualquier tecla para continuar: ");
                    sc.next();
                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    break;
                case 12:
                    System.out.println("\n╔════════════════════════════╗");
                    System.out.println("║ Saliendo del sistema...    ║");
                    System.out.println("╚════════════════════════════╝");
                    salirSwitch = true;
                    break;
            }

        }

        /// GRABAMOS TODO PARA EL FINAL DEL PROGRAMA

        /// GRABAMOS LOS DATOS DE GESTION RESERVA
        JSONUtiles.grabar(new JSONArray().put(gestionReservas.toJSON()),"Reservas.json");

        /// GRABAMOS LOS DATOS DE GESTION CLIENTES
       serializarGestionClientes(gestionClientes,clientesNombreArchivo);

        /// GRABAMOS LOS DATOS DE GESTION EMPLEADOS
        serializarGestionEmpleados(gestionEmpleados,empleadosNombreArchivo);

    }


    /// -----------------------------------------*-----------------------------------------

  /// METODOS PARA LOS CASOS DEL SWITCH
   /// CASE 1
    public void agregarReserva(GestionReserva gestionReservas, Scanner sc)
    {
        String dnicliente;
        LocalDate checkIn = null;
        LocalDate checkOut = null;
        int numeroHabitacionReservada;

        try {
            System.out.println("\n====================[ Agregar Reserva ]====================");
            System.out.println("\n Ingrese el dni del cliente. ");
            dnicliente = sc.next();
            System.out.println("\n Ingrese entre que fecha y que otra fecha va a hacer su reserva. ");

            while (checkIn == null) {
                System.out.println("  Fecha de CheckIn ( AAAA-MM-DD ) : ");
                String fecha = sc.next();
                try {
                    checkIn = LocalDate.parse(fecha);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato inválido. Por favor, intente de nuevo.");
                }
            }

            while (checkOut == null) {
                System.out.println("  Fecha checkOut ( AAAA-MM-DD ) : ");
                String fecha = sc.next();
                try {
                    checkOut = LocalDate.parse(fecha);
                } catch (DateTimeParseException e) {
                    System.out.println("Formato inválido. Por favor, intente de nuevo.");
                }
            }
            System.out.println("\n====================[ Habitaciones Disponibles ]====================");
            System.out.println("\n Estas son las habitaciones del hotel. ");
            ArrayList<Habitacion> auxHabitaciones = gestionReservas.obtenerDisponibles(checkIn, checkOut);
            System.out.println(mostrarAuxListHabitaciones(auxHabitaciones));


            System.out.println("\n Elija una para su reserva. ");
            numeroHabitacionReservada = sc.nextInt();
            Reserva reservaAagregar = new Reserva(dnicliente, checkIn, checkOut, numeroHabitacionReservada);
            reservaAagregar.verificarDatosReserva();
            gestionReservas.confirmarReserva(reservaAagregar);

            System.out.println("\n====================[ Confirmar Reserva ]====================");
            System.out.println("Reserva agregada con exito!");

        }catch (DateTimeParseException | ErrorAlIngresarException | ErrorFechaException | DniDeClienteNoRegistrado | SinDisponibilidadException e)
        {
            System.out.println("Error debido a que: " + e.getMessage());
        }
    }

  /// CASE 2
    public void agregarCliente(GestionGeneral<Cliente> gestionClientes,GestionReserva gestioNReservas, Scanner sc)
    {
       try {
           Cliente cliente = new Cliente();
           sc.nextLine();
           System.out.println("\n====================[ Agregar Cliente ]====================");
           System.out.println("Ingrese el nombre del cliente: ");
           cliente.setNombre(sc.nextLine());
           System.out.println("Ingrese el apellido del cliente: ");
           cliente.setApellido(sc.nextLine());
           System.out.println("Ingrese el dni del cliente: ");
           cliente.setDni(sc.nextLine());
           System.out.println("Ingrese el gmail del cliente: ");
           cliente.setGmail(sc.nextLine());
           System.out.println("Ingrese la nacionalidad del cliente: ");
           cliente.setNacionalidad(sc.nextLine());
           System.out.println("\n====================[ Datos del Domicilio ]====================");
           System.out.println("(DATOS OPCIONALES)");
           System.out.println("Calle: ");
           String calle = sc.nextLine();
           System.out.println("Numero de calle: ");
           int numeroCalle = sc.nextInt();
           sc.nextLine();
           Boolean departamento = null;
           while (departamento == null) {
               System.out.println("Departamento. Si  /  No");
               String respuesta = sc.next();
               if (respuesta.equalsIgnoreCase("si")) {
                   departamento = true;
               } else if (respuesta.equalsIgnoreCase("no")) {
                   departamento = false;
               } else {
                   System.out.println("Respuesta invalida, responder con si o no");
               }
           }
               if(verificarQueExistaGmail(gestionClientes,cliente.getGmail()))
               {
                   throw new ErrorAlIngresarException("El gmail ingresado ya esta registrado en el sistema.");
               }
               cliente.setDomicilio(new Domicilio(calle,numeroCalle,departamento));
               cliente.verificarCliente();
               gestionClientes.agregarObjeto(cliente);
               gestioNReservas.agregarDniDeClienteNuevo(cliente.getDni());

           System.out.println("\n====================[ Cliente Agregado ]====================");
           System.out.println("Cliente agregado con exito!");

       }catch(InputMismatchException | ObjetoYaRegistradoException | DniIngresoException | ErrorAlIngresarException e)
       {
           System.out.println("Error debido a que: " + e.getMessage());
       }
    }

    /// CASE 3
    public void mostrarHistorial(GestionReserva clientes, Scanner sc){
        System.out.println("Ingrese el dni del cliente del cual desea ver el historial: ");
        String dniCliente=sc.next();
        try {
            System.out.println(clientes.verReservasDeCliente(dniCliente));
        } catch (DniDeClienteNoRegistrado e) {
            System.out.println("Error debido a que: " + e.getMessage());
        }
    }

    /// CASE 4
    public void mostrarClientes(GestionGeneral<Cliente> gestionClientes)
    {
        String mensaje = "";
        try {
            gestionClientes.VerificarVacio();

        Iterator<Cliente> recorredor = gestionClientes.getListaRegistros().iterator();
        while (recorredor.hasNext())
        {
           Cliente actual = recorredor.next();
           mensaje = mensaje.concat(actual.toString()+"\n");
        }
        System.out.println("Lista de clientes registrados en el sistema: \n");
        System.out.println(mensaje);
        System.out.println("\n");
        }catch (SinContenidoException e)
        {
            System.out.println("Error debido a que: " + e.getMessage());
        }
    }

    /// CASE 5
    public void mostrarReservas(GestionReserva gestionReservas)
    {
        System.out.println(gestionReservas.mostrarTodasLasReservasPorHabitacion());
        System.out.println("\n");
    }

    /// CASE 6
    public void mostrarHabitaciones(GestionReserva gestionReserva){
        System.out.println(gestionReserva.mostrarHabitaciones());
        System.out.println("\n");
    }

    /// CASE 7
   public void eliminarReserva(GestionReserva gestionReservas, Scanner sc,GestionGeneral<Cliente> gestionClientes)
   {
       System.out.println("\n====================[ Eliminar Reserva ]====================");
       System.out.println("\nIngrese el dni del cliente del cual quiere eliminar una reserva:");
       try {
           Cliente cliente = new Cliente();
           cliente.setDni(sc.next());
           if(!gestionClientes.buscarObjeto(cliente))
           {
               throw new ObjetoNoRegistradoException("El dni no pertenece a ningun objeto del sistema.");
           }
           ArrayList<Reserva> reservasXdni = gestionReservas.buscarReservasXdni(cliente.getDni());
           if(reservasXdni.isEmpty())
           {
               throw new ReservaErrorException("No hay reservas registradas bajo este dni.");
           }
           System.out.println("Reservas actuales bajo el dni " + cliente.getDni() + ":");
           System.out.println(mostrarReservasAux(reservasXdni));
           System.out.println("Ingrese el id de la reserva a eliminar: ");
           int id = sc.nextInt();
           gestionReservas.eliminarReserva(id);
           System.out.println("Reserva eliminado con exito!");

       }catch (ObjetoNoRegistradoException | InputMismatchException | ReservaErrorException e)
       {
           System.out.println("Error debido a que: " + e.getMessage());
       }
   }

     /// CASE 8
    public void eliminarcliente(GestionGeneral<Cliente> gestionClientes, GestionReserva gestionReservas ,Scanner sc){
        System.out.println("\n====================[ Eliminar Cliente ]====================");
        System.out.println("\n Ingrese el dni del cliente que desee borrar de la lista.\n ");
        String dni = sc.next();
        try{
            Cliente aux = new Cliente();
            aux.setDni(dni);
            ArrayList<Reserva> auxArray = gestionReservas.buscarReservasXdni(aux.getDni());
            if(!auxArray.isEmpty())
            {
                throw new ReservaErrorException("Tiene reservas pendientes, eliminelas antes de eliminar al usuario.");
            }
            gestionClientes.eliminarObjeto(aux);
            gestionReservas.eliminarCliente(aux.getDni());
            System.out.println("\n Cliente de dni " +aux.getDni()+ " ha sido eliminado de la lista. ");
        }catch (ObjetoNoRegistradoException | ReservaErrorException e){
            System.out.println("El cliente no se puede eliminar debido a que: " + e.getMessage());
        }
    }

    /// CASE 9
    public void registrarEmpleado(GestionGeneral<Empleado> gestionEmpleados, Scanner sc){
        String nombre;
        String apellido;
        String gmail;
        String contrasenia;

        try{
            System.out.println("\n====================[ Registrar Empleado ]====================");
            System.out.println("\nIngrese el nombre del empleado: ");
            nombre=sc.next();
            System.out.println("\nIngrese el apellido del empleado: ");
            apellido=sc.next();
            System.out.println("\nIngrese el gmail del empleado: ");
            gmail=sc.next();
            System.out.println("\nIngrese la contraseña que desea darle al nuevo recepcionista: ");
            contrasenia=sc.next();

            Empleado emp=new Empleado(nombre,apellido,gmail,contrasenia, TipoEmpleado.RECEPCIONISTA);
            emp.verificarEmpleado();
            gestionEmpleados.agregarObjeto(emp);

            System.out.println("\n====================[ Empleado Agregado ]====================");
            System.out.println("¡Empleado agregado con éxito!");
            System.out.println("==============================================================");
            System.out.println("ID del Empleado: [" + emp.getId() + "]");
            System.out.println("Nombre: [" + emp.getNombre() + "]");
            System.out.println("==============================================================");
            System.out.println("¡No olvides anotar el ID y la contraseña para poder ingresar al sistema!");
            System.out.println("==============================================================");
        }catch (ObjetoYaRegistradoException  | ErrorAlIngresarException e){
            System.out.println("\nError debido a que: "+e.getMessage());
        }
    }

    /// CASE 10
    public void despedirEmpleado(GestionGeneral<Empleado> gestionEmpleados, Scanner sc){
        System.out.println("\n====================[ Despedir Empleado ]====================");
        System.out.println("\nIngrese el ID del empleado que desea despedir: ");
        try{
            int eliminar=sc.nextInt();
            Empleado aux=new Empleado();
            aux.setId(eliminar);
            gestionEmpleados.eliminarObjeto(aux);
            System.out.println("\nEmpleado ha sido despedido con exito! ");
        }catch (ObjetoNoRegistradoException | IdNegativaException  | InputMismatchException e){
            System.out.println("Error debido a que: "+e.getMessage());
        }
    }

    /// CASE 11
    public void mostrarEmpleadosRegistrados(GestionGeneral<Empleado> gestionEmpleados){
        System.out.println(gestionEmpleados.mostrar());
        System.out.println("\n");
    }


    /// -----------------------------------------*-----------------------------------------

    /// AUXILIARES
    public String mostrarAuxListHabitaciones(ArrayList<Habitacion> listaAuxHabitaciones)
    {
        String mensaje = "";
        ListIterator<Habitacion> recorredor = listaAuxHabitaciones.listIterator();
        while (recorredor.hasNext())
        {
            Habitacion actual = recorredor.next();
            mensaje = mensaje.concat(actual.toString()+"\n");
        }
        return mensaje;
    }
    public String mostrarReservasAux(ArrayList<Reserva> listaAuxReservas)
    {
        String mensaje = "";
        ListIterator<Reserva> recorredor = listaAuxReservas.listIterator();
        while (recorredor.hasNext())
        {
            Reserva actual = recorredor.next();
            mensaje = mensaje.concat(actual.toString()+"\n");
        }
        return mensaje;
    }

    public Boolean verificarQueExistaGmail(GestionGeneral<Cliente> gestionclientes, String gmail)
    {
        Boolean retorno = false;
        Iterator<Cliente> recorredor = gestionclientes.getListaRegistros().iterator();
        while (recorredor.hasNext())
        {
            Cliente actual = recorredor.next();
            if(actual.getGmail().equals(gmail))
            {
                retorno=true;
            }
        }
        return retorno;
    }


    /// -----------------------------------------*-----------------------------------------


   /// METODO PARA MODULARIZAR LA SERIALIZACION DE LA GESTION DE EMPLEADOS
    public void serializarGestionEmpleados(GestionGeneral<Empleado> gestionEmpleados, String empleadosNombreArchivo)
    {
        JSONObject objetoRegistroEmpleados = new JSONObject();
        JSONArray arrayListaEmpleados = new JSONArray();
        for (Empleado empleado : gestionEmpleados.getListaRegistros()) {
            arrayListaEmpleados.put(empleado.toJSON());
        }
        try {
            objetoRegistroEmpleados.put("listaEmpleados", arrayListaEmpleados);
            JSONUtiles.grabar(new JSONArray().put(objetoRegistroEmpleados), empleadosNombreArchivo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /// METODO PARA MODULARIZAR LA SERIALIZACION DE LA GESTION DE CLIENTES
     public void serializarGestionClientes(GestionGeneral<Cliente> gestionClientes, String clientesNombreArchivo)
     {
         JSONObject objetoRegistroClientes = new JSONObject();
         JSONArray arrayListaClientes = new JSONArray();
         for (Cliente cliente : gestionClientes.getListaRegistros()) {
             arrayListaClientes.put(cliente.toJSON());
         }
         try {
             objetoRegistroClientes.put("listaClientes", arrayListaClientes);
             JSONUtiles.grabar(new JSONArray().put(objetoRegistroClientes), clientesNombreArchivo);
         } catch (JSONException e) {
             e.printStackTrace();
         }
     }











}

