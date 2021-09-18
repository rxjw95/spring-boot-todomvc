package guide.springboot.sample.tasks;

import lombok.Getter;

@Getter
public class TaskAttribute {
    private final String details;
    private final TaskStatus status;

    public TaskAttribute(final String details, final TaskStatus status) {
        this.details = details;
        this.status = status;
    }
}
