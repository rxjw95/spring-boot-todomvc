package guide.springboot.sample.controller;

import lombok.Getter;

@Getter
public class TaskIdResponse {
    private final String id;

    TaskIdResponse(final String id) {
        this.id = id;
    }
}
