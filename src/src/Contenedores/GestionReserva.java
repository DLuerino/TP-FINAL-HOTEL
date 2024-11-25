package Contenedores;
import Clientes.Cliente;
import Enums.EstadoHabitacion;
import Excepciones.DniDeClienteNoRegistrado;
import Excepciones.DniIngresoException;
import Excepciones.ReservaErrorException;
import Excepciones.SinDisponibilidadException;
import Interfaces.IJSON;
import Reserva.Reserva;
import Reserva.Habitacion;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.*;

public class GestionReserva implements IJSON {
    private HashMap<String, HashSet<Reserva>> listaReservas;
    private ArrayList<Habitacion> listaHabitaciones;
    /// -----------------------------------------*-----------------------------------------
    public GestionReserva(HashMap<String, HashSet<Reserva>> listaReservas) {
        this.listaReservas = new HashMap<>();
        this.listaHabitaciones=new ArrayList<>();
    }

    public GestionReserva(){
        this.listaReservas = new HashMap<>();
        this.listaHabitaciones=new ArrayList<>();
    }
    /// -----------------------------------------*-----------------------------------------
    /// METODOS PARA INICIALIZAR
    public void inciicalizarLasHabitaciones()
    {
     for(int i =1; i<51;i++)
     {
         listaHabitaciones.add(new Habitacion(i,EstadoHabitacion.DISPONIBLE));
     }
    }
    /// -----------------------------------------*-----------------------------------------
    /// METODO CONFIRMAR LA RESERVA
    public void confirmarReserva(Reserva reservita) throws DniDeClienteNoRegistrado,SinDisponibilidadException
    {
        if(!listaReservas.containsKey(reservita.getDniCliente()))
        {
            throw new DniDeClienteNoRegistrado("El dni ingresado no pertenece a ningun cliente de nuestro sistema...");
        }
        ListIterator<Habitacion> recorredor = listaHabitaciones.listIterator();
        while (recorredor.hasNext()) {
            Habitacion actual = recorredor.next();
            if (actual.getNumeroHabitacion()==reservita.getNumeroHabitacionReservada()) {
                if (!actual.estaDisponible(reservita.getCheckIn(), reservita.getCheckOut())) {
                    throw new SinDisponibilidadException("La habitacion se encuentra ocupada para esa fecha");
                } else {
                    listaReservas.get(reservita.getDniCliente()).add(reservita);
                    System.out.println("hola");
                    actual.agregarReserva(reservita);

                }
            }
        }
    }
    /// -----------------------------------------*-----------------------------------------
        /// metodo buscar habitaciones disponibles

    public ArrayList<Habitacion> obtenerDisponibles(LocalDate checkIn, LocalDate checkOut) throws SinDisponibilidadException
    {
        ArrayList<Habitacion> disponibles=new ArrayList<>();

        for(Habitacion habitacion : listaHabitaciones){
            if(habitacion.estaDisponible(checkIn, checkOut)){
                disponibles.add(habitacion);
            }
        }
        if(disponibles.isEmpty())
        {
            throw new SinDisponibilidadException("No hay habitaciones disponibles para la reserva.");
        }

        return disponibles;
    }

    /// -----------------------------------------*-----------------------------------------
    /// metodo para actualizar el estado de las habitaciones

    public void realizarCheckOuts(){
        //LocalDate hoy = LocalDate.of(2024,11,25);
        LocalDate hoy = LocalDate.now();
        for(Habitacion habitacion : listaHabitaciones){
            Iterator<Reserva> iterator = habitacion.getListaReservas().iterator();

            while(iterator.hasNext()){
                Reserva reserva =iterator.next();
                if(reserva.getCheckOut().isBefore(hoy)||reserva.getCheckOut().isEqual(hoy)){
                    /// es decir, la reserva ya termino
                    iterator.remove();///por lo tanto la elimina
                     habitacion.setEstado(EstadoHabitacion.DISPONIBLE);
                }
            }

            /// si la habitacion no tiene reservas activas, se pone como disponible
            if(habitacion.getListaReservas().isEmpty()){
                habitacion.setEstado(EstadoHabitacion.DISPONIBLE);
            }
        }
    }

    public void realizarCheckIns()
    {
        LocalDate hoy = LocalDate.now();
        for(Habitacion habitacion : listaHabitaciones)
        {
            Iterator<Reserva> recorredor = habitacion.getListaReservas().iterator();

            while (recorredor.hasNext())
            {
                Reserva actual = recorredor.next();
                if(actual.getCheckIn().isEqual(hoy))
                {
                    habitacion.setEstado(EstadoHabitacion.OCUPADA);
                }
            }
        }

    }

    /// /// -----------------------------------------*-----------------------------------------

    /// metodo agregar el dni de un cliente recien registrado al hashMap
    public void agregarDniDeClienteNuevo(String dniCliente)
    {
        listaReservas.put(dniCliente,new HashSet<>());
    }


    /// -----------------------------------------*-----------------------------------------
    /// METODOS PARA ELIMINAR

    public void eliminarReserva(int idAeliminar) throws ReservaErrorException
    {
        Boolean mensaje= false;
        ListIterator<Habitacion> recorredor = listaHabitaciones.listIterator();
        while (recorredor.hasNext())
        {
            Habitacion actualHabitacion = recorredor.next();
            ListIterator<Reserva> recorredorAux = actualHabitacion.getListaReservas().listIterator();
            while (recorredorAux.hasNext())
            {
                Reserva actualReserva = recorredorAux.next();
                if(actualReserva.getId()==idAeliminar)
                {
                    recorredorAux.remove();
                    eliminarReservaDeHashMap(idAeliminar,actualReserva.getDniCliente());
                    mensaje = true;
                }
            }
        }
        if(mensaje==false)
        {
            throw new ReservaErrorException("El id ingresado no pertenece a ninguna reserva");
        }
    }

    public void eliminarCliente(String dni)
    {
        listaReservas.remove(dni);
    }


    public void eliminarReservaDeHashMap(int idAeliminar, String dniCliente)
    {
        Iterator<Reserva> recorredor = listaReservas.get(dniCliente).iterator();
        while (recorredor.hasNext())
        {
            Reserva actual = recorredor.next();
            if(actual.getId()==idAeliminar)
            {
                recorredor.remove();
            }
        }
    }

    /// -----------------------------------------*-----------------------------------------
     /// METODOS PARA MOSTRAR

    public String mostrarTodasLasReservas(){
        StringBuilder sb=new StringBuilder();

        /// recorrer hashmap
        for(String dniCliente : listaReservas.keySet()){
            sb.append("[ Reservas del cliente con DNI: ").append(dniCliente).append(" ]\n");

            HashSet<Reserva> reservasCliente=listaReservas.get(dniCliente);
            if(reservasCliente.isEmpty()){
                sb.append(" No tiene reservas.\n");
            }else{
                /// aca se recorre las reservas de ese dni

                for(Reserva reserva : reservasCliente){
                    sb.append(" -").append(reserva.toString()).append("\n");
                }
            }
        }

        /// retornar el SB convertido a String
        return sb.toString();
    }

    /// mostrar el historial de reservas de un cliente

    public String verReservasDeCliente(String dni) throws DniDeClienteNoRegistrado
    {
        StringBuilder sb=new StringBuilder();

        if(!listaReservas.containsKey(dni)){
            throw new DniDeClienteNoRegistrado("El dni ingresado no esta registrado en el sistema.");
        }

        HashSet<Reserva> historial=listaReservas.get(dni);

        sb.append("Reservas del cliente con DNI [").append(dni).append("]:\n");

        if(historial.isEmpty()){
            sb.append("No tiene reservas activas.\n");
        }else {
            for(Reserva reserva : historial){
                sb.append(reserva.toString()).append("\n");
            }
        }

        return sb.toString();
    }


    public String mostrarHabitaciones()
    {
        String mensaje = "";
        ListIterator<Habitacion> recorredor = listaHabitaciones.listIterator();
        while(recorredor.hasNext())
        {
            Habitacion habitacion = recorredor.next();
            mensaje = mensaje.concat(habitacion.toString()+"\n\n");

        }
        return mensaje;
    }


    public String mostrarTodasLasReservasPorHabitacion()
    {
        String mensaje = "";
        ListIterator<Habitacion> recorredor = listaHabitaciones.listIterator();
        while (recorredor.hasNext())
        {
            Habitacion actualHabitacion = recorredor.next();
            ListIterator<Reserva> recorredorAux = actualHabitacion.getListaReservas().listIterator();
            while (recorredorAux.hasNext())
            {
                Reserva actualReserva = recorredorAux.next();
                mensaje = mensaje.concat(actualReserva.toString()+"\n");
            }
        }
        return mensaje;
    }

    public ArrayList<Reserva> buscarReservasXdni(String dni)
    {
        ArrayList<Reserva> reservasDeEseDni = new ArrayList<>();
        ListIterator<Habitacion> recorredor = listaHabitaciones.listIterator();
        while (recorredor.hasNext())
        {
            Habitacion actualHabitacion = recorredor.next();
            ListIterator<Reserva> recorredorAux = actualHabitacion.getListaReservas().listIterator();
            while (recorredorAux.hasNext())
            {
                Reserva actualReserva = recorredorAux.next();
                if(actualReserva.getDniCliente().equals(dni))
                {
                    reservasDeEseDni.add(actualReserva);
                }
            }
        }
        return reservasDeEseDni;
    }

    /// -----------------------------------------*-----------------------------------------
        /// Metodos para serializar y deserializar

    @Override
    public JSONObject toJSON() {
        /// metodo para convertir a JSON

            JSONObject json = new JSONObject();

            try{
                // Convertir listaReservas
                JSONObject reservasJson = new JSONObject();
                for (String key : listaReservas.keySet()) {
                    JSONArray reservasArray = new JSONArray();
                    for (Reserva reserva : listaReservas.get(key)) {
                        reservasArray.put(reserva.toJSON()); // Asumimos que Reserva también tiene toJSONObject
                    }
                    reservasJson.put(key, reservasArray);
                }

                // Convertir listaHabitaciones
                JSONArray habitacionesArray = new JSONArray();
                for (Habitacion habitacion : listaHabitaciones) {
                    habitacionesArray.put(habitacion.toJSON()); // Asumimos que Habitacion tiene toJSONObject
                }

                /// agrega todo al JSON principal
                json.put("listaReservas", reservasJson);
                json.put("listaHabitaciones", habitacionesArray);
            }catch (JSONException e){
                e.printStackTrace();
            }

            return json;
    }

    public static GestionReserva fromJSON(JSONObject obj){
        GestionReserva gestion = new GestionReserva();

        try{
            // Reconstruir listaReservas
            JSONObject reservasJson = obj.getJSONObject("listaReservas");
            Iterator<String> keys = reservasJson.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                JSONArray reservasArray = reservasJson.getJSONArray(key);
                HashSet<Reserva> reservasSet = new HashSet<>();

                for (int i = 0; i < reservasArray.length(); i++) {
                    JSONObject reservaJson = reservasArray.getJSONObject(i);
                    reservasSet.add(Reserva.fromJSON(reservaJson)); // Asumimos que Reserva tiene fromJSONObject
                }

                gestion.listaReservas.put(key, reservasSet);
            }

            // Reconstruir listaHabitaciones
            JSONArray habitacionesArray = obj.getJSONArray("listaHabitaciones");

            for (int i = 0; i < habitacionesArray.length(); i++) {
                JSONObject habitacionJson = habitacionesArray.getJSONObject(i);
                gestion.listaHabitaciones.add(Habitacion.fromJSON(habitacionJson)); // Asumimos que Habitacion tiene fromJSONObject
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

        return gestion;
    }
}