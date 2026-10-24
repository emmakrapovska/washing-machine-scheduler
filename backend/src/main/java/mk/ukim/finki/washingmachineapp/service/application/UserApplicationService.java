package mk.ukim.finki.washingmachineapp.service.application;

import mk.ukim.finki.washingmachineapp.models.dto.*;

import java.util.Optional;

public interface UserApplicationService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
    Optional<RegisterResponse> findByUsername(String username);
}