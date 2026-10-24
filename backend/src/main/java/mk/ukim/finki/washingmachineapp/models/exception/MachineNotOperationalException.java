package mk.ukim.finki.washingmachineapp.models.exception;

public class MachineNotOperationalException extends RuntimeException {
  public MachineNotOperationalException(Long machineId) {
    super("Mashinata so id=" + machineId + " e vo defekt i ne mozhe da se rezervira");
  }
}