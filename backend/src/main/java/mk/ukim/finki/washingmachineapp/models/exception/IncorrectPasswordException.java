package mk.ukim.finki.washingmachineapp.models.exception;

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("Pogresna lozinka");
    }
}