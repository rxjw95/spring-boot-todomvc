package guide.springboot.sample.tasks.repository;

import guide.springboot.sample.tasks.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskServiceConfig {

    @Bean
    TaskService taskService(final TaskRepository taskRepository) {
        return new TaskServiceImpl(taskRepository);
    }
}
