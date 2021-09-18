package guide.springboot.sample.tasks;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    UUID insert(TaskInsertAttribute taskInsertAttribute);
    List<Task> selectAll();
    Optional<Task> select(UUID id);
    TaskAttribute update(UUID id, TaskAttribute taskAttribute);
    TaskAttribute patch(UUID id, TaskAttribute taskAttribute);
    void delete(UUID id);
}
