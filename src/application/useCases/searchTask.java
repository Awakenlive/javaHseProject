package application.useCases;

import domain.entities.Task;
import domain.repository.taskRepository;
import application.common.absractTaskUsecase;
import java.util.List;

public class searchTask extends absractTaskUsecase{
    public searchTask(taskRepository repository){
        super(repository);
    }
    public List<Task> lookTask(String text){
        return repository.findByTitleContaining(text);
    }
}
// принимает текст -> возвращает похожие задачи