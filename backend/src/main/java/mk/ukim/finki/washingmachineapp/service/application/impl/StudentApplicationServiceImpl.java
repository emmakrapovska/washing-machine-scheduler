package mk.ukim.finki.washingmachineapp.service.application.impl;

import mk.ukim.finki.washingmachineapp.models.dto.CreateStudentRequest;
import mk.ukim.finki.washingmachineapp.models.dto.StudentResponse;
import mk.ukim.finki.washingmachineapp.service.application.StudentApplicationService;
import mk.ukim.finki.washingmachineapp.service.domain.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentApplicationServiceImpl implements StudentApplicationService {

    private final StudentService studentService;

    public StudentApplicationServiceImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public Optional<StudentResponse> findById(Long id) {
        return studentService.findById(id).map(StudentResponse::from);
    }

    @Override
    public List<StudentResponse> findAll() {
        return StudentResponse.from(studentService.findAll());
    }

    @Override
    public StudentResponse create(CreateStudentRequest request) {
        return StudentResponse.from(studentService.create(request.toStudent()));
    }

    @Override
    public StudentResponse update(Long id, CreateStudentRequest request) {
        return StudentResponse.from(studentService.update(id,request.toStudent()));
    }

    @Override
    public Optional<StudentResponse> deleteById(Long id) {
        return studentService.deleteById(id).map(StudentResponse::from);
    }
}
