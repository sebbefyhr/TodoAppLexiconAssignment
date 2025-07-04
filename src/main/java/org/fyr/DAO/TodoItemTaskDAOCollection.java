package org.fyr.DAO;

import org.fyr.model.TodoItemTask;

import java.util.Collection;
import java.util.List;

public class TodoItemTaskDAOCollection implements TodoItemTaskDAO{
    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        return null;
    }

    @Override
    public TodoItemTask findById(int id) {
        return null;
    }

    @Override
    public Collection<TodoItemTask> findAll() {
        return List.of();
    }

    @Override
    public Collection<TodoItemTask> findByAssignedStatus(boolean status) {
        return List.of();
    }

    @Override
    public Collection<TodoItemTask> findByPersonId(int personId) {
        return List.of();
    }

    @Override
    public void remove(int id) {

    }
}
