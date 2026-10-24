package mk.ukim.finki.washingmachineapp.service.application.impl;

import mk.ukim.finki.washingmachineapp.helpers.JwtHelper;
import mk.ukim.finki.washingmachineapp.models.domain.Student;
import mk.ukim.finki.washingmachineapp.models.domain.User;
import mk.ukim.finki.washingmachineapp.models.dto.*;
import mk.ukim.finki.washingmachineapp.service.application.UserApplicationService;
import mk.ukim.finki.washingmachineapp.service.domain.StudentService;
import mk.ukim.finki.washingmachineapp.service.domain.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final StudentService studentService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService,
                                      StudentService studentService,
                                      JwtHelper jwtHelper) {
        this.userService = userService;
        this.studentService = studentService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        Student student = new Student();
        student.setName(request.name());
        student.setRoomNumber(request.roomNumber());
        student.setEmail(request.email());
        Student savedStudent = studentService.create(student);

        User user = request.toUser();
        user.setStudent(savedStudent);

        User savedUser = userService.register(user);
        return RegisterResponse.from(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userService.login(request.username(), request.password());
        String token = jwtHelper.generateToken(user);
        return new LoginResponse(token);
    }

    @Override
    public Optional<RegisterResponse> findByUsername(String username) {
        return userService.findByUsername(username)
                .map(RegisterResponse::from);
    }
}