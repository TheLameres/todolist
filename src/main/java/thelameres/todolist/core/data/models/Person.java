package thelameres.todolist.core.data.models;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "PERSON")
@Entity
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Person extends SuperEntity {
    @Length(message = "Name must be >2 and <20", min = 2, max = 20)
    @NotNull(message = "Name must be not null")
    @Column(name = "NAME")
    private String name;

    @JoinColumn(name = "USER_ID")
    @OneToOne(orphanRemoval = true)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;

        return id != null && id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return 1422108840;
    }
}