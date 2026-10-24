package mk.ukim.finki.washingmachineapp.models.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("Korisnikot " + username + " ne postoi");
    }
}