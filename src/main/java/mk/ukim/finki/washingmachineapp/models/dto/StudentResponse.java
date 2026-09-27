package mk.ukim.finki.washingmachineapp.models.dto;

import mk.ukim.finki.washingmachineapp.models.domain.Student;

public record StudentResponse(
        Long id,
        String name,
        String roomNumber,
        String email
) {
    public static StudentResponse from(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getRoomNumber(),
                student.getEmail()
        );
    }
}