package application.useCases;

import domain.entities.Task;
import domain.repository.taskRepository;
import application.common.absractTaskUsecase;
import java.time.LocalDate;

public class editTask extends absractTaskUsecase{

    public editTask(taskRepository repository) {
        super(repository);
    }

    public Task modifyTask(long id, String title, String decription){
        Task task = findTask(id);
        task.setTitle(title);
        task.setDescription(decription);
        return repository.save(task);
    }
}
// принимает задачу по id -> редачит описание