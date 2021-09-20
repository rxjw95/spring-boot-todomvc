package guide.springboot.sample.tasks;

import lombok.Getter;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

@Getter
public class Task {
    private final UUID id;
    private final String details;
    private final TaskStatus status;

    public Task(final UUID id, final String details, final TaskStatus status) {
        this.id = requireNonNull(id);
        this.details = requireNonNull(details);
        this.status = requireNonNull(status);
    }
}
