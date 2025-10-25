package presentation;

import domain.entities.Task;
import domain.entities.priority;
import application.useCases.*;

import java.util.InputMismatchException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.*;


public class consoleUI { //DI - зависимость. так лучше не называть конечно
    private final changePriority changePriorityDI;
    private final createTask createTaskDI;
    private final deadlineCreate deadlineCreateDI;
    private final deleteTask deleteTaskDI;
    private final editTask editTaskDI;
    private final searchTask searchTaskDI;
    private final sortTasks sortTasksDI;
    private final getAllTask showAllTask;

    private Scanner scanner = new Scanner(System.in);

    public consoleUI(changePriority changePriorityDI, createTask createTaskDI, deadlineCreate deadlineCreateDI,
                     deleteTask deleteTaskDI, editTask editTaskDI, searchTask searchTaskDI, sortTasks sortTasksDI, getAllTask showAllTask){
        this.changePriorityDI = changePriorityDI;
        this.createTaskDI = createTaskDI;
        this.deadlineCreateDI = deadlineCreateDI;
        this.deleteTaskDI = deleteTaskDI;
        this.editTaskDI = editTaskDI;
        this.searchTaskDI = searchTaskDI;
        this.sortTasksDI = sortTasksDI;
        this.showAllTask = showAllTask;
    }

    public void start(){
        System.out.println("Привет! Ты используешь TO-DO List! \n");

        while (true){
            printMainMenu();
            int choice = readIntInput();
            menuChoice(choice);
        }
    }

    private void printMainMenu(){
        System.out.println("Главное меню:");
        System.out.println("1. Показать все задачи");
        System.out.println("2. Добавить задачу");
        System.out.println("3. Удалить задачу");
        System.out.println("4. Изменить приоритет задачи");
        System.out.println("5. Поиск задач");
        System.out.println("6. Сортировка задач");
        System.out.println("7 Редактировать задачу");
        System.out.println("0. Выход");
        System.out.println("Выберите пункт: ");
    }
    private int readIntInput(){
        while (true){
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // очистка буфера (убираем знак /n, но нужно ли это здесь?)
                return value;
            }
            catch (InputMismatchException error){
                System.out.println("Неверный ввод! Введите число: ");
                scanner.nextLine();
            }
        }
    }
    private priority readPriorityInput(){
            System.out.println("Смотри, у тебя есть 3 приоритета у задачи: ");
            System.out.println("1 - Низкий");
            System.out.println("2 - Средний");
            System.out.println("3 - Высокий");
            System.out.println("Теперь введи число, я там сам разберусь");

            while (true) {
                int choice = readIntInput();
                switch (choice) {
                    case 1 -> {
                        System.out.println("Установлен низкий приоритет");
                        return priority.LOW;
                    }
                    case 2 -> {
                        System.out.println("Установлен средний приоритет");
                        return priority.MEDIUM;
                    }
                    case 3 -> {
                        System.out.println("Установлен высокий приоритет");
                        return priority.HIGH;
                    }
                    default -> System.out.println("Дурак, у тебя только есть варианты 1, 2, 3. Смотри выше");
                }
            }
    }

    private void printAllTask(List<Task> tasks){
        if (tasks == null || tasks.isEmpty()){
            System.out.println("Нет задач для отображения");
            return;
        }
        for (int i = 0; i < tasks.size(); i++){
            Task task = tasks.get(i);
            String status = task.isCompleted() ? "Задача сделана" : "Задачу делать надо";
            String date = "нет срока";
            if (task.getDate() != null){
                date = task.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            }
            System.out.printf("ID: %d | Название: %s | %s | Приоритет: %s | Срок: %s%n",
                    task.getId(),
                    task.getTitle(),
                    status,
                    task.getPriorityTask(),
                    date
                    );

        }
    }

    private LocalDate readDate(){
        final DateTimeFormatter DATE_PATERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        while (true){
            System.out.println("Введи дату в формате: день/месяц/год: 00.00.2024");
            String setDate = scanner.nextLine().trim();
            try{
                return LocalDate.parse(setDate, DATE_PATERN);
            }
            catch (DateTimeParseException error){
                System.out.println("Неверный формат даты, давай по новой");
            }
        }
    }
    
    private void menuChoice(int choice){
        switch (choice){
            case 1 -> showAllTask();
            case 2 -> addTask();
            case 3 -> removeTask();
            case 4 -> choicePriority();
            case 5 -> seekTask();
            case 6 -> arrangeTask();
            case 7 -> editTask();
            case 0 -> exitFunc();
            default -> System.out.println("Неверный пункт меню!");
        }
    }

    private void editTask() {
        try {
            showAllTask();

            System.out.print("ID задачи для редактирования: ");
            long id = readLongInput();

            System.out.print("Новое название: ");
            String title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("название не может быть пустым!");
                return;
            }

            System.out.print("Новое описание: ");
            String description = scanner.nextLine().trim();
            if (description.isEmpty()) {
                description = null;
            }

            Task updatedTask = editTaskDI.modifyTask(id, title, description);
            System.out.println("Задача отредактирована! ID: " + updatedTask.getId());

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void showAllTask() {
        try {
            List<Task> tasks = showAllTask.showAllTasks();
            if (tasks.isEmpty()){
                System.out.println("Бездельник, у тебя задач нет");
            }
            else {
                System.out.println("Давай закрывай свои задачи: ");
                printAllTask(tasks);
            }
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

    private void addTask() {
        try {
            System.out.println("Добавление новой задачи");

            System.out.println("Введи название для новой задачи");
            String title;
            while (true){
                title = scanner.nextLine().trim();
                if (title.isEmpty()){
                    System.out.println("Название не может быть пустым");
                }
                else{break;}
            }

            System.out.println("Введи описание");
            String description = scanner.nextLine().trim();
            if (description.isEmpty()){
                description = null;
            }

            LocalDate date = readDate();

            System.out.println("Введи приоритет");
            priority priorityTask = readPriorityInput();

            Task task = createTaskDI.newTask(
                    0,
                    title,
                    description,
                    date,
                    priorityTask,
                    false
            );
            System.out.println("Задача создана! ID " + task.getId());
        }
        catch (Exception error){
            System.out.println("Ошибка: " + error);
        }
    }

    private void removeTask() {
        try {
            showAllTask();

            System.out.println("Введи ID задачи");
            long id = readLongInput();

            deleteTaskDI.destroyTask(id);
            System.out.println("Задача удалена");
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e);
        }
    }

    private void choicePriority() {
        try {
            showAllTask();

            System.out.println("Введи ID задачи");
            long id = readIntInput();

            System.out.println("Выбери новый приоритет");
            priority newPriority = readPriorityInput();

            Task updatedTask = changePriorityDI.newPriority(id, newPriority);

            System.out.println("Приоритет задачи с ID: " + id + " изменён на " + newPriority);
        }
        catch (Exception e){
            System.out.println("Ошибка: " + e);
        }
    }

    private void seekTask() {
        try {
            System.out.print("Введите текст для поиска задачи");
            String text = scanner.nextLine().trim();
            if (text.isEmpty()){
                System.out.println("Вы ввели пустой текст");
                return;
            }
            List<Task> foundTasks = searchTaskDI.lookTask(text);
            if (foundTasks.isEmpty()){
                System.out.println("Похожих задач нет");
            }
            else {
                printAllTask(foundTasks);
            }
        }
        catch (Exception e){
            System.out.println("Ошибка " + e.getMessage());
        }
    }

    private void arrangeTask() {
        try {
            System.out.println("Есть несколько типов сортировок");
            System.out.println("1 - сортировать по дате");
            System.out.println("2 - сортировать по приоритету");
            System.out.println("3 - сортировать по выполнености");
            System.out.println("Выбери сортировку");
            int choice = readIntInput();
            List<Task> sortedTask;
            switch (choice) {
                case 1 -> {
                    System.out.println("отсортировано по дате");
                    sortedTask = sortTasksDI.sortByDate();
                }
                case 2 -> {
                    System.out.println("отсортировано по приоритету");
                    sortedTask = sortTasksDI.sortByPriority();
                }
                case 3 -> {
                    System.out.println("отсортировано по выполненности");
                    sortedTask = sortTasksDI.sortByCompleted();
                }
                default -> {
                    System.out.println("Неверный выбор");
                    return;
                }
            }
            printAllTask(sortedTask);
        }
        catch (Exception e){
            System.out.println("Ошибка " + e.getMessage());
        }
    }

    private void exitFunc() {
        scanner.close();
        System.out.println("Выход");
        System.exit(0);
    }
    private long readLongInput() {
        while (true) {
            try {
                long value = scanner.nextLong();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод! Введите число: ");
                scanner.nextLine();
            }
        }
    }
}
