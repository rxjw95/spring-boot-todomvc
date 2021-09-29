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
                                toTaskStatus(taskUpdateRequest.getStatus()));

        final var updatedTaskAttribute = taskService.update(taskId, taskAttribute);

        return toTaskAttributeResponse(updatedTaskAttribute);
    }

    @PatchMapping("/{id}")
    TaskAttributeResponse patch(@PathVariable("id") final String taskIdString,
                                @RequestBody final TaskPatchRequest taskPatchRequest) {
        final var taskId = UUID.fromString(taskIdString);
        final var taskAttribute =
                new TaskPatchAttribute(taskPatchRequest.getDetails(),
                                toTaskStatus(taskPatchRequest.getStatus()));

        final var patchedTaskAttribute = taskService.patch(taskId, taskAttribute);

        return toTaskAttributeResponse(patchedTaskAttribute);
    }

    @PatchMapping
    void patchAll(@RequestBody final List<TaskPatchAllRequest> taskPatchAllRequest) {
        final var taskList =
                taskPatchAllRequest
                .stream()
                .map(request -> new TaskPatchAllAttribute(
                        UUID.fromString(request.getId()),
                        request.getDetails(),
                        toTaskStatus(request.getStatus()))
                ).collect(Collectors.toList());

        taskService.patchAll(taskList);
    }

    @DeleteMapping("/{id}")
    void remove(@PathVariable("id") final String taskIdString) {
        final var taskId = UUID.fromString(taskIdString);
        taskService.delete(taskId);
    }

    @PutMapping
    void removeAll(@RequestBody final List<TaskDeleteRequest> taskIdStringList) {
        final var taskIdList = taskIdStringList.stream().map(taskIdString -> UUID.fromString(taskIdString.getId())).collect(Collectors.toList());
        taskService.deleteAll(taskIdList);
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

    static TaskStatus toTaskStatus(final String status) {
        try{
            return TaskStatus.valueOf(status.toUpperCase(Locale.ENGLISH));
        } catch (Exception e) {
            return null;
        }
    }

}
