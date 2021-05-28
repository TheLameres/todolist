package thelameres.todolist.core.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Table(name = "USER")
@Builder
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class User extends SuperEntity {

    @Column(name = "USERNAME", nullable = false, updatable = false)
    private String username;



    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ENABLED")
    private Boolean enabled;

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    @ToString.Exclude
    @JsonIgnore
    private Person person;

    @JoinColumn(name = "USER_ID")
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @Singular
    private List<Role> roles;

    @Email
    @Column(name = "EMAIL")
    private String email;

}