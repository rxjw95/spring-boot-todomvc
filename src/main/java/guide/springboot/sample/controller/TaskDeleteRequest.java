package guide.springboot.sample.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TaskDeleteRequest {
    private final String id;

    @JsonCreator
    TaskDeleteRequest(@JsonProperty(value = "id", required = true) final String id) {
        this.id = id;
    }
}
