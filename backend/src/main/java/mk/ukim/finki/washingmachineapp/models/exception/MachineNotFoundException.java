package mk.ukim.finki.washingmachineapp.models.exception;

public class MachineNotFoundException extends RuntimeException {
    public MachineNotFoundException(Long id) {
        super("Mashinata so id=" + id + " ne postoi");
    }
}