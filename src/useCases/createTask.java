package useCases;

import domain.entites.Task;
import domain.entites.priority;
import domain.repository.taskRepository;

import java.time.LocalDate;

public class createTask {
    private final taskRepository repository;

    public createTask(taskRepository repository) {
        this.repository = repository;
    }


    public Task createTask(long id, String title, String description,
                           LocalDate date, priority priorityTask, boolean completed){

        if (title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Название не может быть пустым");
        }

        Task task = new Task(id, title, description, date, priorityTask, completed);
        return repository.save(task);
    }
}
