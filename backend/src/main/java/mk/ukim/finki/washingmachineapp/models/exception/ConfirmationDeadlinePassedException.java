package mk.ukim.finki.washingmachineapp.models.exception;

public class ConfirmationDeadlinePassedException extends RuntimeException {
    public ConfirmationDeadlinePassedException(Long bookingId) {
        super("Pominato e vremeto za potvrda na prisustvo za rezervacijata so id=" + bookingId);
    }
}