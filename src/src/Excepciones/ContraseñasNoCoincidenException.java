package Excepciones;

public class ContraseñasNoCoincidenException extends RuntimeException {
    public ContraseñasNoCoincidenException(String message) {
        super(message);
    }
}
