package org.fyr.model;

import org.fyr.Sequencers.TodoItemIdSequencer;

import java.time.LocalDate;
import java.util.Objects;

public class TodoItem {

    private int id;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;
    private int assignee_id;

    private TodoItemIdSequencer tiis = TodoItemIdSequencer.getInstance();

    public TodoItem(){}

    public TodoItem(int id, String title, String description, LocalDate deadline, boolean done, int assignee_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
        this.assignee_id = assignee_id;
    }

    public TodoItem(String title, String description, LocalDate deadline, boolean done, int assignee_id) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
        this.assignee_id = assignee_id;
    }

    /*public TodoItem(String title, String description, LocalDate deadLine, boolean done, Person creator) {
        if((title == null || title.trim().length() == 0) || (deadLine == null)){
            throw new NullPointerException("Title or deadline cant be null or empty");
        }
        this.id = tiis.nextId();
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
        this.creator = creator;
    }
     */

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(int assignee_id) {
        this.assignee_id = assignee_id;
    }

    public boolean isOverdue(){
        LocalDate now = LocalDate.now();
        if(now.isAfter(deadline)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadline +
                ", done=" + done +
                ", assigne_id=" + assignee_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return id == todoItem.id && done == todoItem.done && assignee_id == todoItem.assignee_id && Objects.equals(title, todoItem.title) && Objects.equals(description, todoItem.description) && Objects.equals(deadline, todoItem.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, deadline, done, assignee_id);
    }
}
