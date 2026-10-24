package mk.ukim.finki.washingmachineapp.models.dto;

import mk.ukim.finki.washingmachineapp.models.domain.User;

public record RegisterResponse(
        Long id,
        String username,
        String email,
        String role
) {
    public static RegisterResponse from(User user) {
        return new RegisterResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}