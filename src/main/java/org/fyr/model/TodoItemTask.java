package org.fyr.model;

import org.fyr.Sequencers.TodoItemIdSequencer;
import org.fyr.Sequencers.TodoItemTaskIdSequencer;

import java.util.Objects;


// TODO --REVISIT this class and find if it is properly implemented?!?!
public class TodoItemTask {
    private int id;
    private boolean assigned;
    private TodoItem todoItem;
    private Person assignee;

    private TodoItemTaskIdSequencer titis = TodoItemTaskIdSequencer.getInstance();

    public TodoItemTask() {}

    public TodoItemTask(TodoItem todoItem, Person assignee) {
        if(todoItem == null){
            throw new NullPointerException("Cant be null");
        }
        this.id = titis.nextId();
        this.assigned = assignee != null ? true : false;
        this.todoItem = todoItem;
        this.assignee = assignee;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(TodoItem todoItem) {
        if(todoItem == null){
            throw new NullPointerException("Cant be null");
        }
        this.todoItem = todoItem;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        if (assignee == null) {
            this.assignee = null;
            setAssigned(false);
        } else {
            this.assignee = assignee;
            setAssigned(true);
        }
    }

    @Override
    public String toString() {
        return "TodoItemTask{" +
                "id=" + id +
                ", assigned=" + assigned +
                ", todoItem=" + todoItem +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TodoItemTask that = (TodoItemTask) o;
        return id == that.id && assigned == that.assigned && Objects.equals(todoItem, that.todoItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assigned, todoItem);
    }
}