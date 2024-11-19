package Excepciones;

public class ContraseñasNoCoincideException extends RuntimeException {
    public ContraseñasNoCoincideException(String message) {
        super(message);
    }
}
