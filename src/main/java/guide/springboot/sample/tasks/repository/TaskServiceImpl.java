package guide.springboot.sample.tasks.repository;

import guide.springboot.sample.tasks.TaskService;

/*
* TaskService 구현체 <-> 레파지토리
* */
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
