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

            try {
                gestionEmpleados.agregarObjeto(new Empleado("Facundo", "Galeano", "galeanofacundo752@gmail.com", "utntpfinal", TipoEmpleado.ADMINISTRADOR));
                gestionEmpleados.agregarObjeto(new Empleado("Diego", "Luerino", "lueriodiego@gmail.com", "utntpfinal", TipoEmpleado.ADMINISTRADOR));
                gestionEmpleados.agregarObjeto(new Empleado("Marco", "Olivero", "marcoolivero@gmail.com", "utntpfinal", TipoEmpleado.ADMINISTRADOR));
            } catch (ObjetoYaRegistradoException e) {
                System.out.println("Error debido a que: " + e.getMessage());
            }


            serializarGestionEmpleados(gestionEmpleados,empleadosNombreArchivo);
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
                Cliente cliente = Cliente.fromJSON(jsonCliente); // Deserializa el empleado
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
        System.out.println("Bienvenido al sistema del hotel UTN...");
        while (!salirAux) {
            System.out.println("\n\nPara poder ingresar al sistema debera ingresar su id de usuario.");
            System.out.println("Luego de verificar que exista y le pediremos su contraseña (No la comparta con nadie). Muchas gracias");
            System.out.println("Ingrese su id: ");
            int idIngresada = sc.nextInt();
            empleadoAux = gestionEmpleados.buscarObjetoYretornarlo(new Empleado(idIngresada));
            try {
                if (empleadoAux == null) {
                    throw new ObjetoNoRegistradoException("El id ingresado no tiene relacion con ningun objeto registrado");
                } else {
                    System.out.println("Bienvenido " + empleadoAux.getNombre() + "!!!!!");
                    System.out.println("Ingrese su contraseña:");
                    String contraseñaAux = sc.next();
                    if (empleadoAux.getContraseña().equals(contraseñaAux)) {
                        System.out.println("Contraseña correcta, redireccionandolo al sistema..");
                        salirAux=true;
                    } else {
                        throw new ContraseñasNoCoincideException("La contraseña ingresada es incorrecta.");
                    }

                }
            } catch (ObjetoNoRegistradoException | ContraseñasNoCoincideException e) {
                System.out.println("Error debido a que: " + e.getMessage());
            }
        }

        /// BOOLEANO PARA EL SWITCH
        Boolean salirSwitch = false;
        while (!salirSwitch)
        {
            /// METODO SWITCH PARA ADMINISTRADOR


            System.out.println("\n Bienvenido!!! ");
            System.out.println(" Ingrese 1 si desea registrar una reserva. ");
            System.out.println(" Ingrese 2 si desea registrar un cliente.");
            System.out.println(" Ingrese 3 si desea ver el historial de reservas de un cliente. ");
            System.out.println(" Ingrese 4 si desea ver todos los clientes. ");
            System.out.println(" Ingrese 5 si desea ver todas las reservas. ");
            System.out.println(" Ingrese 6 si desea ver todas las habitaciones. ");
            System.out.println(" Ingrese 7 si desea eliminar una reserva. ");
            System.out.println(" Ingrese 8 si desea eliminar un cliente. ");
            System.out.println(" Ingrese 9 si desea registrar un nuevo empleado.(ADMINISTRADOR)");
            System.out.println(" Ingrese 10 si desea despedir un empleado.(ADMINISTRADOR)");
            System.out.println(" Ingrese 11 si desea ver los empleados registrados.(ADMINISTRADOR)");
            System.out.println(" Ingrese 12 si desea salir del programa. ");
            System.out.println("\n Ingrese su opcion aqui: ");

            int op = 0;
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("\n Opcion 1 elegida. ");
                    agregarReserva(gestionReservas,sc);
                    break;
                case 2:
                    System.out.println("\n Opcion 2 elegida. ");
                    agregarCliente(gestionClientes,gestionReservas,sc);
                    break;
                case 3:
                    System.out.println("\n Opcion 3 elegida. ");
                    mostrarHistorial(gestionReservas, sc);
                    break;
                case 4:
                    System.out.println("\n Opcion 4 elegida. ");
                    mostrarClientes(gestionClientes);
                    break;
                case 5:
                    System.out.println("\n Opcion 5 elegida. ");
                    mostrarReservas(gestionReservas);
                    break;
                case 6:
                    System.out.println("\n Opcion 6 elegida. ");
                    mostrarHabitaciones(gestionReservas);
                    break;
                case 7:
                    System.out.println("\n Opcion 7 elegida. ");
                    break;
                case 8:
                    System.out.println("\n Opcion 8 elegida. ");
                    eliminarcliente(gestionClientes,gestionReservas,sc);
                    break;
                case 9:
                    System.out.println("\n Opcion 9 elegida. ");
                    break;

                case 10:
                    System.out.println("\n Opcion 10 elegida. Saliendo del sistema!! ");
                    salirSwitch = true;
                    break;
            }

        }




        /// GRABAMOS TODO PARA EL FINAL DEL PRGORAMA
        /// GRABAMOS LOS DATOS DE GESTION RESERVA
        JSONUtiles.grabar(new JSONArray().put(gestionReservas.toJSON()),"Reservas.json");

        /// GRABAMOS LOS DATOS DE GESTION CLIENTES
       serializarGestionClientes(gestionClientes,clientesNombreArchivo);

        /// GRABAMOS LOS DATOS DE GESTION EMPLEADOS
        serializarGestionEmpleados(gestionEmpleados,empleadosNombreArchivo);




    }

  /// METODOS PARA LOS CASOS DEL SWITCH

    public void agregarReserva(GestionReserva gestionReservas, Scanner sc)
    {
        String dnicliente;
        LocalDate checkIn = null;
        LocalDate checkOut = null;
        int numeroHabitacionReservada;
        try {
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
            System.out.println("\n Estas son las habitaciones del hotel. ");
            ArrayList<Habitacion> auxHabitaciones = gestionReservas.obtenerDisponibles(checkIn, checkOut);
            System.out.println(mostrarAuxListHabitaciones(auxHabitaciones));
            System.out.println("\n Elija una para su reserva. ");
            numeroHabitacionReservada = sc.nextInt();
            Reserva reservaAagregar = new Reserva(dnicliente, checkIn, checkOut, numeroHabitacionReservada);
            reservaAagregar.verificarDatosReserva();
            gestionReservas.confirmarReserva(reservaAagregar);
            System.out.println("Reserva agregada con exito!");
        }catch (DateTimeParseException | ErrorAlIngresarException | ErrorFechaException | DniDeClienteNoRegistrado | SinDisponibilidadException e)
        {
            System.out.println("Error debido a que: " + e.getMessage());
        }
    }


    public void agregarCliente(GestionGeneral<Cliente> gestionClientes,GestionReserva gestioNReservas, Scanner sc)
    {
       try {
           Cliente cliente = new Cliente();
           sc.nextLine();
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
           System.out.println("Ingrese datos sobre el domicilio del cliente:  ");
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
               cliente.setDomicilio(new Domicilio(calle,numeroCalle,departamento));
               cliente.verificarCliente();
               gestionClientes.agregarObjeto(cliente);
               gestioNReservas.agregarDniDeClienteNuevo(cliente.getDni());
               System.out.println("Cliente agregado con exito!");

       }catch(InputMismatchException | ObjetoYaRegistradoException | DniIngresoException | ErrorAlIngresarException e)
       {
           System.out.println("Error debido a que: " + e.getMessage());
       }
    }

    public void mostrarHistorial(GestionReserva clientes, Scanner sc){
        System.out.println("Ingrese el dni del cliente del cual desea ver el historial: ");
        String dniCliente=sc.next();
        try {
            System.out.println(clientes.verReservasDeCliente(dniCliente));
        } catch (DniDeClienteNoRegistrado e) {
            System.out.println("Error debido a que: " + e.getMessage());
        }
    }

    public void mostrarClientes(GestionGeneral<Cliente> gestionClientes)
    {
        String mensaje = "";
        Iterator<Cliente> recorredor = gestionClientes.getListaRegistros().iterator();
        while (recorredor.hasNext())
        {
           Cliente actual = recorredor.next();
           mensaje = mensaje.concat(actual.toString()+"\n");
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("Lista de clientes registrados en el sistema: \n");
        System.out.println(mensaje);
    }

    public void mostrarReservas(GestionReserva gestionReservas)
    {
        System.out.println(gestionReservas.mostrarTodasLasReservasPorHabitacion());
    }

    public void mostrarHabitaciones(GestionReserva gestionReserva){
        System.out.println(gestionReserva.mostrarHabitaciones());
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    /// 7)-

    public void eliminarcliente(GestionGeneral<Cliente> gestionClientes, GestionReserva gestionReservas ,Scanner sc){
        System.out.println("\n Ingrese el dni del cliente que desee borrar de la lista.\n ");
        String dni = sc.next();
        try{
            Cliente aux = new Cliente();
            aux.setDni(dni);
            gestionClientes.eliminarObjeto(aux);
            gestionReservas.eliminarCliente(aux.getDni());
            System.out.println("\n Cliente de dni " +aux.getDni()+ " ha sido eliminado de la lista. ");
        }catch (ObjetoNoRegistradoException e){
            System.out.println("Error debido a que: " + e.getMessage());
        }
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






}

