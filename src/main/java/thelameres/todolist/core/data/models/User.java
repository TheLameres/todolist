package thelameres.todolist.core.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;

@Table(name = "USERS")
@Builder()
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class User extends SuperEntity {

    @Column(name = "USERNAME", nullable = false, updatable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD")
    @JsonIgnore
    private String password;

    @Column(name = "ENABLED")
    private Boolean enabled;

    @Embedded
    private Person person;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    @Singular
    @JsonManagedReference
    @JsonIgnoreProperties({"createdAt", "updatedAt"})
    private Collection<Role> roles;

    @Email
    @Column(name = "EMAIL", unique = true)
    private String email;

}