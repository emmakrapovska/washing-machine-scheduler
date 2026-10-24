package mk.ukim.finki.washingmachineapp.service.domain;

import mk.ukim.finki.washingmachineapp.models.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<Student> findById(Long id);

    List<Student> findAll();

    Student create(Student student);

    Student update(Long id,Student student);

    Optional<Student> deleteById(Long id);

}
