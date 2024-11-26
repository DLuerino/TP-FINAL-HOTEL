package Excepciones;

public class SinContenidoException extends RuntimeException {
    public SinContenidoException(String message) {
        super(message);
    }
}
