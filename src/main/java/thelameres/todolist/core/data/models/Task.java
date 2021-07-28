package thelameres.todolist.core.data.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "TASK")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
public class Task extends SuperEntity {

    @NotNull
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "text")
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TASK_STATUS")
    private TaskStatus taskStatus;

    @NotNull
    @Column(name = "EXECUTE_BEFORE")
    @Future
    private LocalDateTime executeBefore;

    @ManyToMany
    @JoinTable(name = "task_user_link",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ToString.Exclude
    private List<User> users;

}