package mk.ukim.finki.washingmachineapp.models.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super("Korisnichkoto ime " + username + " e veke zafateno");
    }
}