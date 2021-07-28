package thelameres.todolist.core.rest.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thelameres.todolist.core.data.models.User;
import thelameres.todolist.core.data.responses.AuthRequest;
import thelameres.todolist.core.data.responses.AuthResponse;
import thelameres.todolist.core.data.responses.RegistrationRequest;
import thelameres.todolist.core.data.responses.RegistrationResponse;
import thelameres.todolist.core.services.UserService;
import thelameres.todolist.core.services.auth.JwtService;

@RestController
@RequestMapping("/api/login")
public class AuthController {

    final UserService userService;
    final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        return new AuthResponse(jwtService.generateToken(userService.findByLoginAndPassword(request).getUsername()));
    }

    @PostMapping("/register")
    public RegistrationResponse registration(@RequestBody RegistrationRequest request) {
        User user = userService.save(request);
        String token = jwtService.generateToken(user.getUsername());
        return RegistrationResponse.builder().user(user).token(token).build();
    }
}
