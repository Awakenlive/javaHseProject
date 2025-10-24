package domain.repository;

import domain.entities.Task;
import java.util.List;
import java.util.Optional;

public interface taskRepository {
    Task save(Task task);

    void deleteById(long id);

    Optional<Task> findById(long id);

    List<Task> findAll();

    List<Task> findByTitleContaining(String title);
}
// Здесь интерфейсы без конкретной реализации, их реализация хранится в InMemoryTaskRepository
// Это сделано для того, чтобы domain не зависит от конкретной реализации и её можно подменить если что