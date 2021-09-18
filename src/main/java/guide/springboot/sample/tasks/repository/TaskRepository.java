package guide.springboot.sample.tasks.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TaskRepository extends CrudRepository<TaskEntity, UUID> {
}
