package guide.springboot.sample.controller;

import lombok.Getter;

import static java.util.Objects.requireNonNull;

@Getter
public class TaskAttributeResponse {
    private final String details;
    private final String status;

    TaskAttributeResponse(final String details, final String status) {
        this.details = requireNonNull(details);
        this.status = requireNonNull(status);
    }
}
