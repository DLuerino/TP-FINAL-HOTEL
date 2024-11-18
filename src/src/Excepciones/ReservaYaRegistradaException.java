package Excepciones;

public class ReservaYaRegistradaException extends RuntimeException {
    public ReservaYaRegistradaException(String message) {
        super(message);
    }
}
