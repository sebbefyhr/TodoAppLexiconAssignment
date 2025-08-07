package org.fyr.DAO;

import org.fyr.model.TodoItem;

import java.time.LocalDate;
import java.util.Collection;

public interface TodoItemDAO<T> extends  GeneralDao<T>{

    T findById(int id);
    Collection<T> findAllByDoneStatus(boolean done);
    Collection<T> findByTitleContains(String title);
    Collection<T> findByPersonId(int personId);
    Collection<T> findByDeadlineBefore(LocalDate date);
    Collection<T> findByDeadlineAfter(LocalDate date);
    void remove(int id);
}
