package thelameres.todolist.core.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thelameres.todolist.core.data.responses.AuthRequest;
import thelameres.todolist.core.data.responses.AuthResponse;
import thelameres.todolist.core.services.auth.JwtService;
import thelameres.todolist.core.services.UserService;

@RestController
@RequestMapping("/api/login")
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        return new AuthResponse(jwtService.generateToken(userService.findByLoginAndPassword(request).getUsername()));
    }
}
