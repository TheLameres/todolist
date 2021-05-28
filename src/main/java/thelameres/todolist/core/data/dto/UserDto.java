package thelameres.todolist.core.data.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends SuperDto {
    private String username;
    private Set<String> roles;
}
