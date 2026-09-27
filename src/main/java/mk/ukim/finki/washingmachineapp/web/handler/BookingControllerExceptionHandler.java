package mk.ukim.finki.washingmachineapp.web.handler;

import mk.ukim.finki.washingmachineapp.models.exception.BookingNotFoundException;
import mk.ukim.finki.washingmachineapp.models.exception.MachineNotFoundException;
import mk.ukim.finki.washingmachineapp.models.exception.StudentNotFoundException;
import mk.ukim.finki.washingmachineapp.models.exception.MachineNotOperationalException;
import mk.ukim.finki.washingmachineapp.models.exception.WeeklyBookingLimitExceededException;
import mk.ukim.finki.washingmachineapp.models.exception.BookingOverlapException;
import mk.ukim.finki.washingmachineapp.models.exception.ConfirmationDeadlinePassedException;
import mk.ukim.finki.washingmachineapp.web.controller.BookingController;
import mk.ukim.finki.washingmachineapp.web.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = BookingController.class)
public class BookingControllerExceptionHandler {

    @ExceptionHandler({
            BookingNotFoundException.class,
            MachineNotFoundException.class,
            StudentNotFoundException.class
    })
    public ResponseEntity<ApiError> handleNotFound(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiError.of(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler({
            MachineNotOperationalException.class,
            WeeklyBookingLimitExceededException.class,
            BookingOverlapException.class,
            ConfirmationDeadlinePassedException.class
    })
    public ResponseEntity<ApiError> handleConflict(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiError.of(HttpStatus.CONFLICT, exception.getMessage()));
    }
}