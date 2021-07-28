package thelameres.todolist.core.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import thelameres.todolist.core.repositories.PermissionRepository;
import thelameres.todolist.core.repositories.RoleRepository;
import thelameres.todolist.core.repositories.UserRepository;

@Configuration
@EnableJpaRepositories("thelameres.todolist.core.repositories")
@EnableConfigurationProperties(TheLameresProperties.class)
@EnableTransactionManagement
public class BeansConfigurations {

    @Bean
    @Autowired
    public InitializingBean init(UserRepository userRepository, RoleRepository roleRepository,
                                 PermissionRepository permissionRepository, PasswordEncoder passwordEncoder) {
        return new StartupInitializationBean(userRepository, roleRepository, permissionRepository, passwordEncoder);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String host = "http://localhost:8080";
                registry.addMapping("/api/*").allowedOrigins(host);
                registry.addMapping("/v3/api-docs").allowedOrigins(host);
                registry.addMapping("/v3/api-docs.yaml").allowedOrigins(host);
            }
        };
    }

    @Bean
    public Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
    }
}
