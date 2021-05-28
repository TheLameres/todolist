package thelameres.todolist.core.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thelameres.todolist.core.services.UserService;

import javax.annotation.security.RolesAllowed;

@RolesAllowed("admin")
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;
}
