package mk.ukim.finki.washingmachineapp.service.application;

import mk.ukim.finki.washingmachineapp.models.dto.CreateStudentRequest;
import mk.ukim.finki.washingmachineapp.models.dto.StudentResponse;

import java.util.List;
import java.util.Optional;

public interface StudentApplicationService {
    Optional<StudentResponse> findById(Long id);

    List<StudentResponse> findAll();

    StudentResponse create(CreateStudentRequest request);

    StudentResponse update(Long id, CreateStudentRequest request);

    Optional<StudentResponse> deleteById(Long id);
}
