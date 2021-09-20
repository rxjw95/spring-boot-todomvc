package guide.springboot.sample.controller;

import lombok.Getter;

import static java.util.Objects.requireNonNull;

@Getter
public class TaskIdResponse {
    private final String id;

    TaskIdResponse(final String id) {
        this.id = requireNonNull(id);
    }
}
