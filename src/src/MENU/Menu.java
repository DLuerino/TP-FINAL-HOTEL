package MENU;

import Clientes.Cliente;
import Contenedores.GestionGeneral;
import Contenedores.GestionReserva;
import Excepciones.ObjetoYaRegistradoException;
import JSONUtiles.JSONUtiles;
import MODELOS.Administrador;
import MODELOS.Recepcionista;
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
        GestionGeneral<Recepcionista> gestionRecepcionistas = new GestionGeneral<>();
        GestionGeneral<Administrador> gestionAdministrador = new GestionGeneral<>();
        GestionReserva gestionReservas = new GestionReserva();

        /// STRINGS PARA LOS ARCHIVOS
        String clientesNombreArchivo = "Clientes.json";
        String recepcionistasNombreArchivo = "Recepcionista.json";
        String reservasNombreArchivo = "Reservas.json";
        String administradoresNombreArchivo = "Administradores.josn";

        /// ARCHIVOS QUE UTILIZAREMOS
        File fileClientes = new File(clientesNombreArchivo);
        File fileRecepcionistas = new File(recepcionistasNombreArchivo);
        File fileReservas = new File(reservasNombreArchivo);
        File fileAdministradores = new File(administradoresNombreArchivo);

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

        /// ARCHIVO RECEPCIONISTAS
        if (!fileRecepcionistas.exists()) {
            JSONObject objetoRegistroRecepcionista = new JSONObject();
            JSONArray arrayListaRecepcionista = new JSONArray();
            for (Recepcionista recep : gestionRecepcionistas.getListaRegistros()) {
                arrayListaRecepcionista.put(recep.toJSON());
            }
            try {
                objetoRegistroRecepcionista.put("listaRecepcionistas", arrayListaRecepcionista);
                JSONUtiles.grabar(new JSONArray().put(objetoRegistroRecepcionista), recepcionistasNombreArchivo);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /// ARCHIVO ADMINISTRADOR
        if(!fileAdministradores.exists())
        {
            try{
                String contraseña = "utntpfinal";
                gestionAdministrador.agregarObjeto(new Administrador(1,"Facundo",contraseña));
                gestionAdministrador.agregarObjeto(new Administrador(2,"Diego",contraseña));
                gestionAdministrador.agregarObjeto(new Administrador(3,"Marco",contraseña));
            }catch (ObjetoYaRegistradoException e)
            {
                System.out.println("Ocurrio un error debido a que: " + e.getMessage());
            }
            JSONObject objetoRegistroAdmins = new JSONObject();
            JSONArray arratListaAdministradores = new JSONArray();
            for(Administrador admin : gestionAdministrador.getListaRegistros())
            {
                arratListaAdministradores.put(admin.toJSON());
            }
            try{
                objetoRegistroAdmins.put("listaAdministradores",arratListaAdministradores);
                JSONUtiles.grabar(new JSONArray().put(objetoRegistroAdmins),administradoresNombreArchivo);
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

            /// LEEMOS Y GUARDAMOS LA GESTION GENERAL (GENERICA) QUE CONTIENE LOS RECEPCIONISTAS
            JSONArray auxRecepcionistas = new JSONArray(JSONUtiles.leer(recepcionistasNombreArchivo));
            for(int r=0;r<auxRecepcionistas.length();r++)
            {
                JSONObject objetoRecepcionista = auxRecepcionistas.getJSONObject(r);
                gestionRecepcionistas.agregarObjeto(Recepcionista.fromJSON(objetoRecepcionista));
            }

            /// Y POR ULTIMO LEEMOS Y GUARDAMOS LA GESTION GENERAL (GENERICA) QUE CONTIENE A LOS ADMINISTRADORES
            JSONArray auxAdmins = new JSONArray(JSONUtiles.leer(administradoresNombreArchivo));
            for (int a=0;a<auxAdmins.length();a++)
            {
                JSONObject objetoAdmin = auxAdmins.getJSONObject(a);
                gestionAdministrador.agregarObjeto(Administrador.fromJSON(objetoAdmin));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ///








    }
}