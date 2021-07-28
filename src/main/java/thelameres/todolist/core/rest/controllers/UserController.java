package thelameres.todolist.core.rest.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thelameres.todolist.core.data.models.User;
import thelameres.todolist.core.services.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api/users")
public class UserController implements DataController<User, UUID> {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User findById(UUID uuid) {
        return userService.findById(uuid);
    }

    @Override
    public User findByName(String name) {
        return userService.findByLogin(name);
    }

    @GetMapping("/me")
    public User getMyUser() {
        return userService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public List<User> findAll() {
        return userService.getAllUsers();
    }

    @Override
    public User save(User entity) {
        return userService.save(entity);
    }

    @Override
    public User save(UUID uuid, User entity) {
        return save(entity);
    }

    @Override
    public void delete(UUID uuid) {
        userService.delete(uuid);
    }

    @Override
    public Map<String, Integer> count() {
        return Collections.singletonMap("count", (int) userService.count());
    }
}
