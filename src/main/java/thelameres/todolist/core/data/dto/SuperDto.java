package thelameres.todolist.core.data.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class SuperDto {
    protected UUID id;
    protected String createdAt;
    protected String updatedAt;
}
