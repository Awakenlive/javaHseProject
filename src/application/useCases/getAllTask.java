package application.useCases;

import domain.entities.Task;
import domain.repository.taskRepository;
import java.util.List;

public class getAllTask{
    private final taskRepository repository;

    public getAllTask(taskRepository repository) {
        this.repository = repository;
    }

    public List<Task> showAllTasks(){
        return repository.findAll();
    }
}
