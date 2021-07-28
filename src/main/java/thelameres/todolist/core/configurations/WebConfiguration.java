package thelameres.todolist.core.configurations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import thelameres.todolist.core.data.models.User;
import thelameres.todolist.core.repositories.RoleRepository;
import thelameres.todolist.core.repositories.UserRepository;
import thelameres.todolist.core.rest.filters.JwtFilter;
import thelameres.todolist.core.services.auth.JPAUserDetailService;

import java.util.stream.Collectors;

@EnableWebSecurity
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Configuration
@Slf4j
public class WebConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtFilter jwtFilter;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public WebConfiguration(JwtFilter jwtFilter, UserRepository userRepository, RoleRepository roleRepository) {
        this.jwtFilter = jwtFilter;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/persons").authenticated()
                .antMatchers("/api/tasks").authenticated()
                .antMatchers("/api/users").authenticated()
                .antMatchers("/api/login/*").permitAll()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console", "/h2-console/**", "swagger-ui.html", "/v3/api-docs.yaml", "/v3/api-docs/");
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new JPAUserDetailService(userRepository);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public RoleHierarchy roleHierarchy() {
        var roleHierarchy = new RoleHierarchyImpl();
        var collect = roleRepository.findAll().stream().map(role -> {
            if (role.getParent() != null) {
                return role.getParent().getName() + " > " + role.getName();
            } else return "\n";
        }).collect(Collectors.joining(" \n "));
        log.info(collect);
        roleHierarchy.setHierarchy(collect);
        return roleHierarchy;
    }

    @Bean
    public AuditorAware<User> auditorAware() {
        return new SpringSecurityAuditorAware();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }
}
