package thelameres.todolist.core.data.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
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
    private String name;

    @JoinColumn(name = "PARENT_ID")
    @OneToOne(orphanRemoval = true)
    @JsonIgnoreProperties({"createdAt", "updatedAt", "permissions", "parent"})
    private Role parent;

    @ManyToMany(mappedBy = "roles")
    @ToString.Exclude
    @Singular
    @JsonBackReference
    private Collection<User> users;

    @ToString.Exclude
    @JoinTable(name = "ROLE_PERMISSION_LINK",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID"))
    @ManyToMany
    @OrderBy("name DESC")
    @Singular
    @JsonManagedReference
    @JsonIgnoreProperties({"createdAt", "updatedAt"})
    private List<Permission> permissions;
}
