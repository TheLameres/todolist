package thelameres.todolist.core.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("thelameres.todolist")
@Getter
@Setter
public class TheLameresProperties {
    private String token;
}
