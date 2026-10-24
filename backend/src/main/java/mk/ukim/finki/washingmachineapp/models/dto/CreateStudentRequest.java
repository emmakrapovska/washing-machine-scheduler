package mk.ukim.finki.washingmachineapp.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import mk.ukim.finki.washingmachineapp.models.domain.Student;

public record CreateStudentRequest(
        @NotBlank
        String name,
        @NotBlank
        String roomNumber,
        @NotBlank @Email
        String email
) {
        public Student toStudent() {
                return new Student(name, roomNumber,email);
        }
}