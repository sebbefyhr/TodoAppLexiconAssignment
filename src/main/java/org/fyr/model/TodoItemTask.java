package org.fyr.model;

public class TodoItemTask {
    private int id;
    private boolean assigned;
    private TodoItem todoItem;
    private Person assignee;

    public TodoItemTask() {

    }

    public TodoItemTask(int id, TodoItem todoItem, Person assignee) {
        this.id = id;
        this.assigned = assignee != null ? true : false;
        this.todoItem = todoItem;
        this.assignee = assignee;
    }

    public TodoItemTask(TodoItem todoItem, Person assignee) {
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

    public void setAssigned(/*boolean assigned*/) {

        this.assigned = assignee != null ? true : false;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public void setTodoItem(TodoItem todoItem) {
        this.todoItem = todoItem;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        if (assignee == null) {
            this.assignee = null;
            setAssigned();
        } else {
            this.assignee = assignee;
            setAssigned();
        }
    }
}
