package Excepciones;

public class SinDisponibilidadException extends RuntimeException {
  public SinDisponibilidadException(String message) {
    super(message);
  }
}
