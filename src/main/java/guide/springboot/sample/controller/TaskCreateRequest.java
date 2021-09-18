package guide.springboot.sample.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TaskCreateRequest {
    private final String details;

    @JsonCreator
    TaskCreateRequest(
            @JsonProperty(value = "details", required = true) final String details
    ) {
        this.details = details;
    }
}
