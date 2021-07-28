package thelameres.todolist.core.data.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Person {

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Column(name = "POSITION")
    private String position;
}