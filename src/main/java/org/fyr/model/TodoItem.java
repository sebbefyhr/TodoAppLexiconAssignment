package org.fyr.model;

import java.time.LocalDate;

public class TodoItem {

    private int id;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private Person creator;

    public TodoItem(){}

    public TodoItem(int id, String title, String description, LocalDate deadLine, boolean done, Person creator) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
        this.creator = creator;
    }


    public TodoItem(String title, String description, LocalDate deadLine, boolean done, Person creator) {
        if((title == null || title.trim().length() == 0) || (deadLine == null)){
            throw new NullPointerException("Fields cant be null or empty");
        }
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.done = done;
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
            throw new NullPointerException("Deadline cant be null");
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

    public String getSummary(){
        return "{id: " + id +
                ", title: " +title +
                ", descritpion: " +description +
                ", deadline: " + deadLine +
                ", done: " + done +
                ", creator: " + creator.getSummary() +
                "}";
    }

    public boolean isOverdue(){
        LocalDate now = LocalDate.now();
        if(now.isAfter(deadLine)){
            return true;
        }
        return false;
    }
}
