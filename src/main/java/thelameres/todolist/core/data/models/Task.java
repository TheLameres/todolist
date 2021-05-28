package thelameres.todolist.core.data.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    @Column(name = "TASK_NAME")
    private String taskName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TASK_STATUS")
    private TaskStatus taskStatus;

    @JoinColumn(name = "PERSON_ID")
    @OneToOne(orphanRemoval = true)
    private Person person;

    @NotNull
    @Column(name = "EXECUTE_BEFORE")
    @Future
    private LocalDateTime executeBefore;

}