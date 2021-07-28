package thelameres.todolist.core.data.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import thelameres.todolist.core.utils.events.LoggerEventListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@EntityListeners({LoggerEventListener.class, AuditingEntityListener.class})
@Getter
@Setter
public abstract class SuperEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", updatable = false, nullable = false)
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    protected UUID id;

    @Column(name = "CREATED_AT")
    @CreatedDate
    protected LocalDateTime createdDate;

    @Column(name = "UPDATED_AT")
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;

    @JoinColumn(name = "CREATED_BY")
    @OneToOne
    @CreatedBy
    protected User createdBy;

    @JoinColumn(name = "LAST_MODIFIED_BY")
    @OneToOne
    @LastModifiedBy
    protected User lastModifiedBy;

}
