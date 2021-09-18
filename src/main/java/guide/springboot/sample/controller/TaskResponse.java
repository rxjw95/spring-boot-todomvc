package guide.springboot.sample.controller;

import lombok.Getter;

@Getter
public class TaskResponse extends TaskIdResponse {
    private final String details;
    private final String status;

    TaskResponse(final String id, final String details, final String status) {
        super(id);
        this.details = details;
        this.status = status;
    }
}
