package infrastructure;

import domain.entities.Task;
import domain.repository.taskRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryTaskRepository implements taskRepository{
    private final Map<Long, Task> tasks = new HashMap<>();

    @Override
    public Task save(Task task){
        if (task.getId() == 0){
            long newId = findMaxId() + 1;
            task.setId(newId);
        }
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public void deleteById(long id){
        tasks.remove(id);
    }

    @Override
    public Optional<Task> findById(long id){
        Task task = tasks.get(id);
        return Optional.ofNullable(task);

    }
    @Override
    public List<Task> findAll(){
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Task> findByTitleContaining(String title){
        String lowerCaseQuery = title.toLowerCase();
        return tasks.values()
                .stream()
                .filter(task -> task.getTitle().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }

    private long findMaxId(){
        if (tasks.isEmpty()) {return 0;}

        long maxId = 0;

        for (Long id: tasks.keySet()){
            if (id > maxId){
                maxId = id;
            }
        }
        return maxId;
    }

}
