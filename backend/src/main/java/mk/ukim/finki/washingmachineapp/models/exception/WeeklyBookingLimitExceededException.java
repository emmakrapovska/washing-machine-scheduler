package mk.ukim.finki.washingmachineapp.models.exception;

public class WeeklyBookingLimitExceededException extends RuntimeException {
    public WeeklyBookingLimitExceededException(Long studentId, int limit) {
        super("Studentot so id=" + studentId + " go dostigna nedelniot limit od " + limit + " rezervacii");
    }
}