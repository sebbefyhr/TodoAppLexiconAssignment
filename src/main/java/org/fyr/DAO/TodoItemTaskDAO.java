package org.fyr.DAO;

import org.fyr.model.TodoItemTask;

import java.util.Collection;

public interface TodoItemTaskDAO<T> {

    T persist(T todoItemTask);
    T findById(int id);
    Collection<T> findAll();
    Collection<T> findByAssignedStatus(boolean status);
    Collection<T> findByPersonId(int personId);
    void remove(int id);
}
