package thelameres.todolist.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import thelameres.todolist.core.data.models.User;
import thelameres.todolist.core.data.responses.AuthRequest;
import thelameres.todolist.core.data.responses.RegistrationRequest;
import thelameres.todolist.core.repositories.RoleRepository;
import thelameres.todolist.core.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User getUserById(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }

    public User findByLogin(String login) {
        return userRepository.findByUsername(login).orElseThrow(EntityNotFoundException::new);
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public User findByLoginAndPassword(AuthRequest authRequest) {
        return findByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword());
    }

    public User save(@NotNull String username, @NotNull String password, @NotNull String email) {
        return userRepository.save(User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .role(roleRepository.findByRole("user")
                                .orElseThrow(EntityNotFoundException::new))
                .enabled(true).build());
    }

    public User save(RegistrationRequest request) {
        return save(request.getUsername(), request.getPassword(), request.getEmail());
    }
}
