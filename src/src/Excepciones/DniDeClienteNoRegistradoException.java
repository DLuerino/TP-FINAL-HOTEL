package Excepciones;

public class DniDeClienteNoRegistradoException extends RuntimeException {
    public DniDeClienteNoRegistradoException(String message) {
        super(message);
    }
}
