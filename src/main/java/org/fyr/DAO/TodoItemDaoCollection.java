package org.fyr.DAO;

import org.fyr.model.TodoItem;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class TodoItemDaoCollection implements TodoItemDAO{



    @Override
    public TodoItem persist(TodoItem todoItem) {
        return null;
    }

    @Override
    public TodoItem findById(int id) {
        return null;
    }

    @Override
    public Collection<TodoItem> findAll() {
        return List.of();
    }

    @Override
    public Collection<TodoItem> findAllByDoneStatus(boolean done) {
        return List.of();
    }

    @Override
    public Collection<TodoItem> findByTitleContains(String title) {
        return List.of();
    }

    @Override
    public Collection<TodoItem> findByPersonId(int personId) {
        return List.of();
    }

    @Override
    public Collection<TodoItem> findByDeadlineBefore(LocalDate date) {
        return List.of();
    }

    @Override
    public Collection<TodoItem> findByDeadlineAfter(LocalDate date) {
        return List.of();
    }

    @Override
    public void remove(int id) {

    }
}
