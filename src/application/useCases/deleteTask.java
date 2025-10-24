package application.useCases;

import domain.repository.taskRepository;
import application.common.absractTaskUsecase;

public class deleteTask extends absractTaskUsecase{
    public deleteTask(taskRepository repository){
        super(repository);
    }
    public void destroyTask(long id){
        findTask(id); // если не найдено должен выбросить исключение
        repository.deleteById(id);
    }
}
// принимает задачу по id -> удаляет задачу