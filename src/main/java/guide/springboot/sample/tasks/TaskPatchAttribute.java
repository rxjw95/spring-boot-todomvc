package guide.springboot.sample.tasks;

import lombok.Getter;

@Getter
public class TaskPatchAttribute {
    private final String details;
    private final TaskStatus status;

    public TaskPatchAttribute(final String details, final TaskStatus status) {
        this.details = details;
        this.status = status;
    }
}
