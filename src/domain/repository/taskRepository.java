package domain.repository;

import domain.entites.Task;
import java.util.List;
import java.util.Optional;

public interface taskRepository {
    Task save(Task task);

    void deleteById(long id);

    Optional<Task> findById(long id);

    List<Task> findAll();

    List<Task> findByTitleContaining(String title);
}
