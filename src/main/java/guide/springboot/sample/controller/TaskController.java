package guide.springboot.sample.controller;


import guide.springboot.sample.tasks.Task;
import guide.springboot.sample.tasks.TaskAttribute;
import guide.springboot.sample.tasks.TaskInsertAttribute;
import guide.springboot.sample.tasks.TaskService;
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
