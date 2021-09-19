package guide.springboot.sample.controller;


import guide.springboot.sample.tasks.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TaskIdResponse create(@RequestBody final TaskCreateRequest taskCreateRequest) {
        final var taskInsertAttribute = new TaskInsertAttribute(taskCreateRequest.getDetails());
        final var uuid = taskService.insert(taskInsertAttribute);

        return new TaskIdResponse(uuid.toString());
    }

    @GetMapping
    List<TaskResponse> retrieveAll() {
        final var tasks = taskService.selectAll();

        return tasks.stream()
                .map(TaskController::toTaskResponse)
                .collect(Collectors.toUnmodifiableList());
    }

    @GetMapping("/{id}")
    ResponseEntity<TaskAttributeResponse> retrieveById(@PathVariable("id") final String taskIdString) {
        final var taskId = UUID.fromString(taskIdString);
        final var taskAttribute = taskService.select(taskId);

        return ResponseEntity.of(taskAttribute.map(TaskController::toTaskAttributeResponse));
    }

    @PutMapping("/{id}")
    TaskAttributeResponse update(@PathVariable("id") final String taskIdString,
                                 @RequestBody final TaskUpdateRequest taskUpdateRequest) {

        final var taskId = UUID.fromString(taskIdString);
        final var taskAttribute =
                new TaskAttribute(taskUpdateRequest.getDetails(),
                TaskStatus.valueOf(taskUpdateRequest.getStatus().toUpperCase(Locale.ENGLISH)));

        final var updatedTaskAttribute = taskService.update(taskId, taskAttribute);

        return toTaskAttributeResponse(updatedTaskAttribute);
    }

    @DeleteMapping("/{id}")
    void remove(@PathVariable("id")final String taskIdString) {
        final var taskId = UUID.fromString(taskIdString);
        taskService.delete(taskId);
    }

    static TaskResponse toTaskResponse(final Task task) {
        return new TaskResponse(
                task.getId().toString(),
                task.getDetails(),
                task.getStatus().name().toLowerCase(Locale.ENGLISH));
    }

    static TaskAttributeResponse toTaskAttributeResponse(final TaskAttribute task) {
        return new TaskAttributeResponse(
                task.getDetails(),
                task.getStatus().name().toLowerCase(Locale.ENGLISH));
    }

}
