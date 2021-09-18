package guide.springboot.sample.controller;


import guide.springboot.sample.tasks.Task;
import guide.springboot.sample.tasks.TaskInsertAttribute;
import guide.springboot.sample.tasks.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
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
        TaskInsertAttribute taskInsertAttribute = new TaskInsertAttribute(taskCreateRequest.getDetails());
        return new TaskIdResponse(taskService.insert(taskInsertAttribute).toString());
    }

    @GetMapping
    List<TaskResponse> retrieveAll() {
        final var tasks = taskService.selectAll();

        return tasks.stream()
                .map(TaskController::toTaskResponse)
                .collect(Collectors.toUnmodifiableList());
    }



    static TaskResponse toTaskResponse(final Task task) {
        return new TaskResponse(
                task.getId().toString(),
                task.getDetails(),
                task.getStatus().name().toLowerCase(Locale.ENGLISH));
    }
}
