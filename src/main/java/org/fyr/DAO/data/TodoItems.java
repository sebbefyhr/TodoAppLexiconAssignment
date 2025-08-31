package org.fyr.DAO.data;

import java.util.Collection;

public interface TodoItems {

    Todo create(Todo todo);
    Collection<Todo> findAll();
    Todo findById(int id);
    Collection<Todo> findByDoneStatus(boolean status);
    Collection<Todo> findByAssignee(int id);
    Collection<Todo> findByAssignee(PersonNew person);
    Collection<Todo> findByUnassignedTodoItems();
    Todo update(Todo todo);
    boolean deleteById(int id);

}
