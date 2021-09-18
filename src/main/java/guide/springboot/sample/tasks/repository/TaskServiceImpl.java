package guide.springboot.sample.tasks.repository;

import guide.springboot.sample.tasks.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public Optional<Task> select(UUID id) {
        return Optional.empty();
    }

    @Override
    public TaskAttribute update(UUID id, TaskAttribute taskAttribute) {
        return null;
    }

    @Override
    public TaskAttribute patch(UUID id, TaskAttribute taskAttribute) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    static Task toTask(final TaskEntity taskEntity) {
        return new Task(taskEntity.getId(), taskEntity.getDetails(), taskEntity.getStatus());
    }
}
