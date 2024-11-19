package MENU;

import Clientes.Cliente;
import Contenedores.GestionGeneral;
import Contenedores.GestionReserva;
import Enums.TipoEmpleado;
import Excepciones.ContraseñaIncorrectaException;
import Excepciones.ObjetoNoRegistradoException;
import Excepciones.ObjetoYaRegistradoException;
import JSONUtiles.JSONUtiles;
import MODELOS.Empleado;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;

public class Menu {

    /// MENU DEL PROGRAMA

    /// -------------------------------------------------*-------------------------------------------------
    /// CONSTRUCTOR
    public Menu(){
    }
    ///-------------------------------------------------*-------------------------------------------------

    /// METODO PARA EJECUTAR EL MENU DEL PROGRAMA
    public void ejecutarMenu() {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        /// GESTIONES QUE UTILIZAREMOS
        GestionGeneral<Cliente> gestionClientes = new GestionGeneral<>();
        GestionGeneral<Empleado> gestionEmpleados = new GestionGeneral<>();
        GestionReserva gestionReservas = new GestionReserva();

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
            JSONObject objetoRegistroClientes = new JSONObject();
            JSONArray arrayListaClientes = new JSONArray();
            /// SERIALIZAMOS UN OBJETO DE CLASE GESTIONGENERAL QUE TIENE COMO DATO CLIENTE
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

        /// ARCHIVO EMPLEADOS
        if(!fileEmpleados.exists())
        {

            try{
                gestionEmpleados.agregarObjeto(new Empleado("Facundo","Galeano","galeanofacundo752@gmail.com","utntpfinal", TipoEmpleado.ADMINISTRADOR));
                gestionEmpleados.agregarObjeto(new Empleado("Diego","Luerino","lueriodiego@gmail.com","utntpfinal", TipoEmpleado.ADMINISTRADOR));
                gestionEmpleados.agregarObjeto(new Empleado("Marco","Olivero","marcoolivero@gmail.com","utntpfinal", TipoEmpleado.ADMINISTRADOR));
            }catch (ObjetoYaRegistradoException e)
            {
                e.printStackTrace();
            }


            JSONObject objetoRegistroEmpleados = new JSONObject();
            JSONArray arrayListaEmpleados = new JSONArray();
            for(Empleado empleado : gestionEmpleados.getListaRegistros())
            {
                arrayListaEmpleados.put(empleado.toJSON());
            }
            try{
                objetoRegistroEmpleados.put("listaEmpleados",arrayListaEmpleados);
                JSONUtiles.grabar(new JSONArray().put(objetoRegistroEmpleados),empleadosNombreArchivo);
            }catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        /// AHORA REALIZAMOS EL CODIGO QUE LEE LOS ARCHIVOS DEL PROGRAMA Y GUARDA LOS DATOS EN LAS VARIABLES GESTIONES ASI TRABAJAMOS CON ELLAS Y SUS DATOS
        /// LEEMOS Y GUARDAMOS LA GESTION RESERVAS
        try {
            /// LEEMOS Y GUARDAMOS LA GESTION RESERVAS
            JSONArray auxReservas = new JSONArray(JSONUtiles.leer(reservasNombreArchivo));
            gestionReservas = GestionReserva.fromJSON(auxReservas.getJSONObject(0));

            /// AHORA DEBERIAMOS LEER Y GUARDAR LA GESTION GENERAL (GENERICA) QUE CONTIENE LOS CLIENTES
            JSONArray auxClientes = new JSONArray(JSONUtiles.leer(clientesNombreArchivo));
            for(int c=0;c<auxClientes.length();c++)
            {
                JSONObject objetoCliente = auxClientes.getJSONObject(c);
                gestionClientes.agregarObjeto(Cliente.fromJSON(objetoCliente));
            }

            /// LEEMOS Y GUARDAMOS LA GESTION GENERAL (GENERICA) QUE CONTIENE EMPLEADOS
        JSONArray auxEmpleados = new JSONArray(JSONUtiles.leer(empleadosNombreArchivo));
        for(int e=0;e<auxEmpleados.length();e++)
        {
            JSONObject objetoEmpleado = auxEmpleados.getJSONObject(e);
            gestionEmpleados.agregarObjeto(Empleado.fromJSON(objetoEmpleado));
        }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        ///
        Boolean salirAux = false;

       while(salirAux)
        System.out.println("Bienvenido al sistema del hotel UTN...");
        System.out.println("Ingrese su id: ");
        int idIngresada = sc.nextInt();
        Empleado empleadoAux = gestionEmpleados.buscarObjetoYretornarlo(new Empleado(idIngresada));
    try {
        if (empleadoAux == null) {
            throw new ObjetoNoRegistradoException("El id ingresado no tiene relacion con ningun objeto registrado");
        } else {
            System.out.println("Bienvenido " + empleadoAux.getNombre() + "!!!!!");
            System.out.println("Ingrese su contraseña:");
            String contraseñaAux = sc.next();
            if (empleadoAux.getContraseña().equals(contraseñaAux)) {
                System.out.println("Contraseña correcta, redireccionandolo al sistema..");
                salirAux = true;
            } else {
                throw new ContraseñaIncorrectaException("La contraseña ingresada es incorrecta.");
            }
        }
    }catch (ObjetoNoRegistradoException | ContraseñaIncorrectaException e) {
        e.printStackTrace();
    }





    }
}