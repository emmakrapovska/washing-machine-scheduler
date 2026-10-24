package mk.ukim.finki.washingmachineapp.models.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(Long id) {
        super("Rezervacijata so id=" + id + " ne postoi");
    }
}