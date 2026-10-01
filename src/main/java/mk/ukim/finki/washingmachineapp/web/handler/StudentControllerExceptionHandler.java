package mk.ukim.finki.washingmachineapp.web.handler;

import mk.ukim.finki.washingmachineapp.models.exception.StudentNotFoundException;
import mk.ukim.finki.washingmachineapp.web.controller.StudentController;
import mk.ukim.finki.washingmachineapp.web.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = StudentController.class)
public class StudentControllerExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(StudentNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiError.of(HttpStatus.NOT_FOUND, exception.getMessage()));
    }
}