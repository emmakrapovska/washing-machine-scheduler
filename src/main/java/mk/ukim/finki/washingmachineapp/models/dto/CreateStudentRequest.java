package mk.ukim.finki.washingmachineapp.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateStudentRequest(
        @NotBlank
        String name,
        @NotBlank
        String roomNumber,
        @NotBlank @Email
        String email
) {
}