package Excepciones;

public class ContraseñaIncorrectaException extends RuntimeException {
    public ContraseñaIncorrectaException(String message) {
        super(message);
    }
}
