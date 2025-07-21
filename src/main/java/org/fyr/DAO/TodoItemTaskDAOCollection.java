package org.fyr.DAO;

import org.fyr.model.TodoItemTask;

import java.util.Collection;
import java.util.List;


//TODO -- • Use the Stream API in implementing DAO classes
public class TodoItemTaskDAOCollection implements TodoItemTaskDAO{

    private static TodoItemTaskDAOCollection todoItemTaskDAOCollection;

    private TodoItemTaskDAOCollection(){}

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

    public static TodoItemTaskDAOCollection getInstance(){
        if(todoItemTaskDAOCollection == null){
            todoItemTaskDAOCollection = new TodoItemTaskDAOCollection();
        }
        return todoItemTaskDAOCollection;
    }
}
