package application.common;

import domain.entities.priority;
import domain.repository.taskRepository;
import domain.entities.Task;

import java.util.Optional;

public abstract class absractTaskUsecase { // абстракция, для useCase, которые используют поиск по id

    protected final taskRepository repository; // Наша абстракция, которую нельзя подменить
    // мы через интерфейс устанавливаем поле repository для хранения зависимости
    // final нужен чтобы поле нельзя было подменить. Поле вообще задаётся один раз в main

    public absractTaskUsecase(taskRepository repository) { // конструктор для Dependency injection
        this.repository = repository;
    }

    protected Task findTask(long id) { // protected нужен, чтобы только насследники
        // могли использовать эту функцию
        Optional<Task> optionalTask = repository.findById(id);

        if (optionalTask.isEmpty()) {
            throw new IllegalArgumentException("Задача с ID: " + id + " не найдена"); // обработаем в main
        }
        return optionalTask.get(); //достаём нашу задачу из обёртки. Обёртка вообще создана чтобы отлавливать проверку на пустоты
    }
}
