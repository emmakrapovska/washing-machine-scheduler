package mk.ukim.finki.washingmachineapp.web.handler;

import mk.ukim.finki.washingmachineapp.models.exception.MachineNotFoundException;
import mk.ukim.finki.washingmachineapp.web.controller.MachineController;
import mk.ukim.finki.washingmachineapp.web.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = MachineController.class)
public class MachineControllerExceptionHandler {

    @ExceptionHandler(MachineNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(MachineNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiError.of(HttpStatus.NOT_FOUND, exception.getMessage()));
    }
}