package guide.springboot.sample.tasks;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*
 * controller -> service <- domain (OCP)
 */
public interface TaskService {

    UUID insert(TaskInsertAttribute taskInsertAttribute);
    List<Task> selectAll();
    Optional<TaskAttribute> select(UUID id);
    TaskAttribute update(UUID id, TaskAttribute taskAttribute);
    TaskAttribute patch(UUID id, TaskAttribute taskAttribute);
    void delete(UUID id);
}
