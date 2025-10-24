package application.useCases;

import domain.entities.Task;
import domain.entities.priority;
import domain.repository.taskRepository;
import application.common.absractTaskUsecase;


public class changePriority extends absractTaskUsecase{ // наследуем

    public changePriority(taskRepository repository) {
        super(repository); // инициализируем с нужным нам репозиторием
    }

    public Task newPriority(long id, priority changedPriority){
        Task task = findTask(id);
        task.setPriorityTask(changedPriority);
        return repository.save(task); //возращаем так а не просто task, ибо use case не должен знать про id
        // плюс если просто возращать задачу, нет сохранения (сохранение происходит в из-за вызванной функции save
    }
}
// принимает задачу по id -> меняем приоритет