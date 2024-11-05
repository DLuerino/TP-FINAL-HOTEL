package Users;

import Personas.Persona;

public class User <T extends Persona>{
    private T usuario;
    private String contrasenia;

    public User(T usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }


}
