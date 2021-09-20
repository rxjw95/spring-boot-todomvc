package guide.springboot.sample.tasks.repository;

import guide.springboot.sample.tasks.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Objects.requireNonNullElse;

/*
* TaskService 구현체 <-> 레파지토리
* */
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public UUID insert(TaskInsertAttribute taskInsertAttribute) {
        final var taskEntity = new TaskEntity(taskInsertAttribute.getDetails(), TaskStatus.ACTIVE);
        final var savedEntity = taskRepository.save(taskEntity);
        return savedEntity.getId();
    }

    @Override
    public List<Task> selectAll() {
        final var taskEntities = taskRepository.findAll();

        return StreamSupport.stream(taskEntities.spliterator(), false)
                .map(TaskServiceImpl::toTask)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<TaskAttribute> select(UUID id) {
        final var taskEntity = taskRepository.findById(id);
        return taskEntity.map(TaskServiceImpl::toTaskAttribute);
    }

    @Override
    public TaskAttribute update(UUID id, TaskAttribute taskAttribute) {
        final var taskEntity = new TaskEntity(id, taskAttribute.getDetails(), taskAttribute.getStatus());
        final var updatedEntity = taskRepository.save(taskEntity);
        return toTaskAttribute(updatedEntity);
    }

    @Override
    public TaskAttribute patch(UUID id, TaskPatchAttribute taskAttribute) {
        final var taskEntity = taskRepository.findById(id);
        final var patchedEntity =
                taskEntity.map(task -> new TaskEntity(
                    task.getId(),
                    requireNonNullElse(taskAttribute.getDetails(), task.getDetails()),
                    requireNonNullElse(taskAttribute.getStatus(), task.getStatus())
                )
            ).orElseThrow();

        final var updatedEntity = taskRepository.save(patchedEntity);

        return toTaskAttribute(updatedEntity);
    }

    @Override
    public void delete(UUID id) {
        if(taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        }
    }

    static Task toTask(final TaskEntity taskEntity) {
        return new Task(taskEntity.getId(), taskEntity.getDetails(), taskEntity.getStatus());
    }

    static TaskAttribute toTaskAttribute(final TaskEntity taskEntity) {
        return new TaskAttribute(taskEntity.getDetails(), taskEntity.getStatus());
    }
}
