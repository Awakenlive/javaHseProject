package application.useCases;

import domain.entities.Task;
import domain.repository.taskRepository;
import application.common.absractTaskUsecase;
import java.time.LocalDate;

public class deadlineCreate extends absractTaskUsecase{
    public deadlineCreate(taskRepository repository){
        super(repository);
    }
    public Task newDeadline(long id, LocalDate newData){
        Task task = findTask(id);
        task.setDate(newData);
        return repository.save(task);
    }
}
// принимает задачу по id -> создаём дату