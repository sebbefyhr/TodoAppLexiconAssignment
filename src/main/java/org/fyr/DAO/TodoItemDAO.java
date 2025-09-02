package org.fyr.DAO;

import org.fyr.model.Person;
import org.fyr.model.TodoItem;

import java.util.Collection;

public interface TodoItemDAO {

    TodoItem create(TodoItem todo);
    Collection<TodoItem> findAll();
    TodoItem findById(int id);
    Collection<TodoItem> findByDoneStatus(boolean status);
    Collection<TodoItem> findByAssignee(int id);
    Collection<TodoItem> findByAssignee(Person person);
    Collection<TodoItem> findByUnassignedTodoItems();
    TodoItem update(TodoItem todo);
    boolean deleteById(int id);
}
