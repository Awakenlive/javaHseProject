package domain.entites;

import java.time.LocalDate;

public class Task {
    private long id;
    private String title;
    private String description;
    private LocalDate date;
    private priority priorityTask;
    private boolean completed;

    public Task(long id, String title, String description, LocalDate date, priority priorityTask, boolean completed){
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.priorityTask = priorityTask;
        this.completed = completed;
    }


    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}

    public priority getPriorityTask() {return priorityTask;}
    public void setPriorityTask(priority priorityTask) {this.priorityTask = priorityTask;}

    public boolean isCompleted() {return completed;}
    public void setCompleted(boolean completed) {this.completed = completed;}


}
