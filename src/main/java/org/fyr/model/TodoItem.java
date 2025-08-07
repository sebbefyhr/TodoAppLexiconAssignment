package org.fyr.model;

import org.fyr.Sequencers.TodoItemIdSequencer;

import java.time.LocalDate;
import java.util.Objects;

public class TodoItem {

    private int id;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private Person creator;

    private TodoItemIdSequencer tiis = TodoItemIdSequencer.getInstance();

    public TodoItem(){}


    public TodoItem(String title, String description, LocalDate deadLine, boolean done, Person creator) {
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

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title == null || title.trim().length() == 0){
            throw new NullPointerException("Title cant be null or empty");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        if(deadLine == null){
            throw new NullPointerException("Deadline cant be empty");
        }
        this.deadLine = deadLine;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    public boolean isOverdue(){
        LocalDate now = LocalDate.now();
        if(now.isAfter(deadLine)){
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
                ", deadLine=" + deadLine +
                ", done=" + done +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return id == todoItem.id && done == todoItem.done && Objects.equals(title, todoItem.title) && Objects.equals(description, todoItem.description) && Objects.equals(deadLine, todoItem.deadLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, deadLine, done);
    }
}
