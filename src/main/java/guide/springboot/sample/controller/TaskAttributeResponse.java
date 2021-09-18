package guide.springboot.sample.controller;

import lombok.Getter;

@Getter
public class TaskAttributeResponse {
    private final String details;
    private final String status;

    TaskAttributeResponse(final String details, final String status) {
        this.details = details;
        this.status = status;
    }
}
