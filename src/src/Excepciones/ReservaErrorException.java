package Excepciones;

public class ReservaErrorException extends RuntimeException {
    public ReservaErrorException(String message) {
        super(message);
    }
}
