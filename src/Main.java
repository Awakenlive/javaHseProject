import domain.repository.taskRepository;
import infrastructure.InMemoryTaskRepository;
import presentation.consoleUI;
import application.useCases.*;

public class Main {
    public static void main(String[] args) {
        try{
            System.out.print("Запускаем программу");
            taskRepository repository = new InMemoryTaskRepository();
            changePriority switchPriority = new changePriority(repository);
            createTask newTask = new createTask(repository);
            deadlineCreate ddCreate = new deadlineCreate(repository);
            deleteTask removeTask = new deleteTask(repository);
            editTask modifyTask = new editTask(repository);
            getAllTask receiveTask = new getAllTask(repository);
            searchTask exploreTask = new searchTask(repository);
            sortTasks arrangeTask = new sortTasks(repository);

            consoleUI console = new consoleUI(
                    switchPriority,
                    newTask,
                    ddCreate,
                    removeTask,
                    modifyTask,
                    exploreTask,
                    arrangeTask,
                    receiveTask
            );
            console.start();
        }
        catch (Exception e){
            System.out.print("Ошибка " + e);
        }
    }
}

//