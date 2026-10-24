package mk.ukim.finki.washingmachineapp.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import mk.ukim.finki.washingmachineapp.models.domain.User;
import mk.ukim.finki.washingmachineapp.models.enums.Role;

public record RegisterRequest(
        @NotBlank String username,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8, message = "Lozinkata mora da ima najmalku 8 karakteri") String password,
        @NotBlank String name,
        @NotBlank String roomNumber
) {
    public User toUser() {
        User user = new User();
        user.setUsername(username());
        user.setEmail(email());
        user.setPassword(password());
        user.setRole(Role.STUDENT);
        return user;
    }
}