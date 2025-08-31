package org.fyr.DAO.data;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

public class TodoItemsImpl implements TodoItems{

    private Connection conn;

    public TodoItemsImpl(Connection connection){
        this.conn = connection;
    }

    @Override
    public Todo create(Todo todo) {
        return null;
    }

    @Override
    public Collection<Todo> findAll() {
        return List.of();
    }

    @Override
    public Todo findById(int id) {
        return null;
    }

    @Override
    public Collection<Todo> findByDoneStatus(boolean status) {
        return List.of();
    }

    @Override
    public Collection<Todo> findByAssignee(int id) {
        return List.of();
    }

    @Override
    public Collection<Todo> findByAssignee(PersonNew person) {
        return List.of();
    }

    @Override
    public Collection<Todo> findByUnassignedTodoItems() {
        return List.of();
    }

    @Override
    public Todo update(Todo todo) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
