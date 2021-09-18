package guide.springboot.sample.tasks;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Task {
    private final UUID id;
    private final String details;
    private final TaskStatus status;

    public Task(final UUID id, final String details, final TaskStatus status) {
        this.id = id;
        this.details = details;
        this.status = status;
    }
}
