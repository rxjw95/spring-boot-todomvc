package guide.springboot.sample.tasks;

import lombok.Getter;

import static java.util.Objects.requireNonNull;

@Getter
public class TaskAttribute {
    private final String details;
    private final TaskStatus status;

    public TaskAttribute(final String details, final TaskStatus status) {
        this.details = requireNonNull(details);
        this.status = requireNonNull(status);
    }
}
