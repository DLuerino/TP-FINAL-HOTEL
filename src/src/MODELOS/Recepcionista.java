package MODELOS;

import Excepciones.ErrorAlIngresarException;

import java.util.Objects;
import java.util.Random;

public class Recepcionista extends Persona{

    private int id;

    public Recepcionista(String nombre, String apellido, String gmail, String contraseña, int id) {
        super(nombre, apellido, gmail, contraseña);
        this.id = generarId();
    }

    public Recepcionista() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recepcionista that = (Recepcionista) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Recepcionista{" +super.toString()+
                "id=" + id +
                '}';
    }

    private static int generarId(){
        Random random = new Random();
        return random.nextInt(1_000_000) + 1;
    }

    public void verificarRecepcionista() throws ErrorAlIngresarException {
        if(this.nombre.isEmpty() || this.apellido.isEmpty() || this.contraseña.isEmpty() || this.gmail.isEmpty()){
            throw new ErrorAlIngresarException("\n Complete el espacio vacio. ");
        }
    }

}
