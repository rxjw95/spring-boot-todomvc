package guide.springboot.sample.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TaskPatchAllRequest {
    private final String id;
    private final String details;
    private final String status;

    @JsonCreator
    TaskPatchAllRequest(@JsonProperty(value = "id", required = true) final String id,
                        @JsonProperty(value = "details", required = true) final String details,
                        @JsonProperty(value = "status", required = true) final String status) {
        this.id = id;
        this.details = details;
        this.status = status;
    }

}
