package thelameres.todolist.core.data.responses;

import lombok.Data;

@Data
public class PersonRequest {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String position;
}
