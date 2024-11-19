package MENU;

import Clientes.Cliente;
import Contenedores.GestionGeneral;
import Contenedores.GestionReserva;
import JSONUtiles.JSONUtiles;
import MODELOS.Recepcionista;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.sql.SQLOutput;
import java.util.Scanner;

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
        boolean salir = false;
        int opcion;
        /// GESTIONES QUE UTILIZAREMOS
        GestionGeneral<Cliente> gestionClientes = new GestionGeneral<>();
        GestionGeneral<Recepcionista> gestionRecepcionistas = new GestionGeneral<>();
        GestionReserva gestionReservas = new GestionReserva();

        /// STRINGS PARA LOS ARCHIVOS
        String clientesNombreArchivo = "Clientes.json";
        String recepcionistasNombreArchivo = "Recepcionista.json";
        String reservasNombreArchivo = "Reservas.json";

        /// ARCHIVOS QUE UTILIZAREMOS
        File fileClientes = new File(clientesNombreArchivo);
        File fileRecepcionistas = new File(recepcionistasNombreArchivo);
        File fileReservas = new File(reservasNombreArchivo);

        /// VERIFICACIONES PARA SABER SI LOS ARCHIVOS EXISTEN O NO (SI NO EXISTEN LOS CREAMOS E INICIALIZAMOS LOS DATOS NECESARIOS PARA EL SISTEMA)
        if (!fileReservas.exists()) {
            gestionReservas.inciicalizarLasHabitaciones();
            JSONUtiles.grabar(new JSONArray().put(gestionReservas.toJSON()), reservasNombreArchivo);
        }

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

        /// AHORA REALIZAMOS EL CODIGO QUE LEE LOS ARCHIVOS DEL PROGRAMA Y GUARDA LOS DATOS EN LAS VARIABLES GESTIONES ASI TRABAJAMOS CON ELLAS Y SUS DATOS
        /// LEEMOS Y GUARDAMOS LA GESTION RESERVAS
        try {
            /// LEEMOS Y GUARDAMOS LA GESTION RESERVAS
            JSONArray aux = new JSONArray(JSONUtiles.leer(reservasNombreArchivo));
            gestionReservas = GestionReserva.fromJSON(aux.getJSONObject(0));

            /// AHORA DEBERIAMOS LEER Y GUARDAR LA GESTION GENERAL (GENERICA) QUE CONTIENE CLIENTES


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}