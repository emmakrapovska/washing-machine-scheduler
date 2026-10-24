package mk.ukim.finki.washingmachineapp.service.domain.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.washingmachineapp.models.domain.Student;
import mk.ukim.finki.washingmachineapp.models.exception.StudentNotFoundException;
import mk.ukim.finki.washingmachineapp.repository.StudentRepository;
import mk.ukim.finki.washingmachineapp.service.domain.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student update(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException(id));
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setRoomNumber(student.getRoomNumber());
        return studentRepository.save(existingStudent);

    }

    @Override
    public Optional<Student> deleteById(Long id) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        existingStudent.ifPresent(studentRepository::delete);
        return existingStudent;
    }
}
