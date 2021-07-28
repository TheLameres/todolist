package thelameres.todolist.core.configurations;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import thelameres.todolist.core.data.models.JPAUserDetails;
import thelameres.todolist.core.data.models.User;

import java.util.Optional;


@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(JPAUserDetails.class::cast)
                .map(JPAUserDetails::getUser).or(Optional::empty);
    }
}
