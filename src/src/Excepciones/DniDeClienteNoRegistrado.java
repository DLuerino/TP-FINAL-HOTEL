package Excepciones;

public class DniDeClienteNoRegistrado extends RuntimeException {
    public DniDeClienteNoRegistrado(String message) {
        super(message);
    }
}
