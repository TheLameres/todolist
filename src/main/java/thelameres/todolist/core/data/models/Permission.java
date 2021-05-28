package thelameres.todolist.core.data.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "PERMISSION")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Permission extends SuperEntity {
    String permission;

    @ManyToMany(mappedBy = "permissions")
    @ToString.Exclude
    private List<Role> roles;
}