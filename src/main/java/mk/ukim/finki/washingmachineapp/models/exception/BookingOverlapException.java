package mk.ukim.finki.washingmachineapp.models.exception;

public class BookingOverlapException extends RuntimeException {
    public BookingOverlapException(Long machineId) {
        super("Terminot e veke zafaten za mashinata so id=" + machineId);
    }
}