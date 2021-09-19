package guide.springboot.sample.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TaskUpdateRequest {
    private final String details;
    private final String status;

    @JsonCreator
    TaskUpdateRequest(@JsonProperty(value="details", required = true) final String details,
                      @JsonProperty(value="status", required = true) final String status) {
        this.details = details;
        this.status = status;
    }
}
