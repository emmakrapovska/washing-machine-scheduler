package mk.ukim.finki.washingmachineapp.web.controller;

import jakarta.validation.Valid;
import mk.ukim.finki.washingmachineapp.models.domain.User;
import mk.ukim.finki.washingmachineapp.models.dto.*;
import mk.ukim.finki.washingmachineapp.models.exception.UserNotFoundException;
import mk.ukim.finki.washingmachineapp.service.application.UserApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return userApplicationService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return userApplicationService.login(request);
    }

    @GetMapping("/me")
    public RegisterResponse me(@AuthenticationPrincipal User user) {
        return userApplicationService.findByUsername(user.getUsername())
                .orElseThrow(() -> new UserNotFoundException(user.getUsername()));
    }
}