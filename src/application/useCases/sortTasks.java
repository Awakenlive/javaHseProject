package application.useCases;

import domain.entities.Task;
import domain.repository.taskRepository;
import application.common.absractTaskUsecase;
import java.util.List;

import java.util.Comparator;



public class sortTasks extends absractTaskUsecase{
    public sortTasks(taskRepository repository){
        super(repository);
    }
    public List<Task> sortByDate(){
        List<Task> tasks = repository.findAll();
        tasks.sort(Comparator.comparing( // компаратор - правило сравнение
                Task::getDate,
                Comparator.nullsLast(Comparator.naturalOrder()) // сортируем(null в конец или чтоб ошибки не было(сортируем по дате))
        )); // high, medium, low
        return tasks;
    }

    public List<Task> sortByPriority(){
        List<Task> tasks = repository.findAll();
        tasks.sort(Comparator.comparing(Task::getPriorityTask).reversed());
        return tasks;
    }

    public List<Task> sortByCompleted(){
        List<Task> tasks = repository.findAll();
        tasks.sort(Comparator.comparing(Task::isCompleted)); // сначала невыполненные
        return tasks;
    }
}
// сортирует по каким-то особенностям задачи