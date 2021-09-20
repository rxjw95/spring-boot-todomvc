package guide.springboot.sample.tasks;

import lombok.Getter;

import static java.util.Objects.requireNonNull;

@Getter
public class TaskInsertAttribute {
    private final String details;

    public TaskInsertAttribute(final String details) {
        this.details = requireNonNull(details);
    }
}
