package guide.springboot.sample.tasks.repository;

import guide.springboot.sample.tasks.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "taks")
@Getter
@Setter
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String details;
    private TaskStatus status;

    protected TaskEntity() {}

    TaskEntity(final UUID id, final String details, final TaskStatus status) {
        this.id = id;
        this.details = details;
        this.status = status;
    }

    TaskEntity(final String details, final TaskStatus status) {
        this(null, details, status);
    }
}
