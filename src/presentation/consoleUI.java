package presentation;

import domain.entities.Task;
import domain.entities.priority;
import domain.repository.taskRepository;
import application.useCases.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.*;
import java.util.TreeMap;


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
        System.out.println("Привет! Ты используешь TO-DO List!");

        while (true){
            printMainMenu();
            int choice = readIntInput();
        }
    }

    private void printMainMenu(){
        System.out.println("\n Главное меню:");
        System.out.println("1. Показать все задачи");
        System.out.println("2. Добавить задачу");
        System.out.println("3. Удалить задачу");
        System.out.println("4. Изменить приоритет задачи");
        System.out.println("5. Поиск задач");
        System.out.println("6. Сортировка задач");
        System.out.println("0. Выход");
        System.out.print("Выберите пункт: ");
    }
    private int readIntInput(){
        while (true){
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // очистка буфера (убираем знак /n, но нужно ли это здесь?)
                return value;
            }
            catch (NumberFormatException error){
                System.out.print("Неверный ввод! Введите число: ");
                scanner.nextLine();
            }
        }
    }
    private priority readPriorityInput(){
            System.out.print("Смотри, у тебя есть 3 приоритета у задачи: ");
            System.out.print("1 - Низкий");
            System.out.print("2 - Средний");
            System.out.print("3 - Высокий");
            System.out.print("Теперь введи число, я там сам разберусь");

            while (true) {
                int choice = readIntInput();
                switch (choice) {
                    case 1 -> {
                        System.out.print("Установлен низкий приоритет");
                        return priority.LOW;
                    }
                    case 2 -> {
                        System.out.print("Установлен средний приоритет");
                        return priority.MEDIUM;
                    }
                    case 3 -> {
                        System.out.print("Установлен высокий приоритет");
                        return priority.HIGH;
                    }
                    default -> System.out.print("Дурак, у тебя только есть варианты 1, 2, 3. Смотри выше");
                }
            }
    }

    private void printAllTask(List<Task> tasks){
        
    }

    private LocalDate readDate(){
        final DateTimeFormatter DATE_PATERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        while (true){
            System.out.print("Введи дату в формате: день/месяц/год: 00.00.2024");
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
            case 0 -> exitFunc();
        }
    }

    private void showAllTask() {
        try {
            List<Task> tasks = showAllTask.showAllTasks();
            if (tasks.isEmpty()){
                System.out.print("Бездельник, у тебя задач нет");
            }
            else {
                System.out.print("Давай закрывай свои задачи: ");
                printAllTask(tasks);
            }
        }
        catch (Exception e){
            System.out.print("Ошибка: " + e.getMessage());
        }

    }

    private void addTask() {
    }

    private void removeTask() {
    }

    private void choicePriority() {
    }

    private void seekTask() {
    }

    private void arrangeTask() {
    }

    private void exitFunc() {
    }

}
