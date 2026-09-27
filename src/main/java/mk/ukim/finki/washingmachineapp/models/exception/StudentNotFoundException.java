package mk.ukim.finki.washingmachineapp.models.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super("Studentot so id=" + id + " ne postoi");
    }
}