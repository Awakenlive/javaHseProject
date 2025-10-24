package application.useCases;

import domain.entities.Task;
import domain.entities.priority;
import domain.repository.taskRepository;

import java.time.LocalDate;

public class createTask {
    private final taskRepository repository;

    public createTask(taskRepository repository) {
        this.repository = repository;
    }


    public Task newTask(long id, String title, String description,
                           LocalDate date, priority priorityTask, boolean completed){

        if (title == null || title.trim().isEmpty()){
            throw new IllegalArgumentException("Название не может быть пустым");
        }

        Task task = new Task(id, title, description, date, priorityTask, completed);
        return repository.save(task);
    }
}
