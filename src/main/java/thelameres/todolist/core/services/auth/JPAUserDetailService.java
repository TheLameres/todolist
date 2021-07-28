package thelameres.todolist.core.services.auth;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import thelameres.todolist.core.data.models.JPAUserDetails;
import thelameres.todolist.core.repositories.UserRepository;

@Service
public class JPAUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public JPAUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public JPAUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new JPAUserDetails(userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("Can't find user")));
    }
}

