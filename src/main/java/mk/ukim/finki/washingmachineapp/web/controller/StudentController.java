package mk.ukim.finki.washingmachineapp.web.controller;

import jakarta.validation.Valid;
import mk.ukim.finki.washingmachineapp.models.dto.CreateStudentRequest;
import mk.ukim.finki.washingmachineapp.models.dto.StudentResponse;
import mk.ukim.finki.washingmachineapp.models.exception.StudentNotFoundException;
import mk.ukim.finki.washingmachineapp.service.application.StudentApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentApplicationService studentApplicationService;

    public StudentController(StudentApplicationService studentApplicationService) {
        this.studentApplicationService = studentApplicationService;
    }

    @GetMapping
    public List<StudentResponse> findAll() {
        return studentApplicationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> findById(@PathVariable Long id) {
        return studentApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse create(@Valid @RequestBody CreateStudentRequest request) {
        return studentApplicationService.create(request);
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id, @Valid @RequestBody CreateStudentRequest request) {
        return studentApplicationService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponse> deleteById(@PathVariable Long id) {
        return studentApplicationService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }
}