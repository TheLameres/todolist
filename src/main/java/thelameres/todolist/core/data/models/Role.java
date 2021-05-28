package thelameres.todolist.core.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "ROLE")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Role extends SuperEntity {
    @Column(name = "ROLE")
    private String role;

    @JoinColumn(name = "PARENT_ID")
    @OneToOne(orphanRemoval = true)
    private Role parent;

    @ToString.Exclude
    @JoinTable(name = "ROLE_PERMISSION_LINK",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID"))
    @ManyToMany
    @OrderBy("permission DESC")
    private List<Permission> permissions;

    @ToString.Exclude
    @JsonIgnore
    @JoinColumn(name = "USER_ID")
    @ManyToOne(cascade = {CascadeType.ALL})
    private User user;
}
