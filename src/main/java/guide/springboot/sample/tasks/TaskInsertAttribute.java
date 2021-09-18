package guide.springboot.sample.tasks;

import lombok.Getter;

@Getter
public class TaskInsertAttribute {
    private final String details;

    TaskInsertAttribute(final String details) {
        this.details = details;
    }
}
