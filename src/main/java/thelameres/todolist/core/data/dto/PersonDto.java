package thelameres.todolist.core.data.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto extends SuperDto {
    private String name;
    private String user;
}
