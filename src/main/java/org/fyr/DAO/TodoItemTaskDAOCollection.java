package org.fyr.DAO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.fyr.model.AppUser;
import org.fyr.model.TodoItemTask;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class TodoItemTaskDAOCollection implements TodoItemTaskDAO<TodoItemTask> {

    private static TodoItemTaskDAOCollection todoItemTaskDAOCollection;
    private String file = "src/main/resources/protocols/TodoItemTask.json";

    private List<TodoItemTask> todoItemTasks;
    private ObjectMapper mapper = new ObjectMapper();


    private TodoItemTaskDAOCollection() {
        todoItemTasks = new ArrayList<>();
    }

    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        if (todoItemTasks.contains(todoItemTask)) {
            return todoItemTask;
        }

        todoItemTasks.add(todoItemTask);
        return todoItemTask;
    }

    @Override
    public TodoItemTask findById(int id) {
        return todoItemTasks.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<TodoItemTask> findAll() {
        return todoItemTasks;
    }

    @Override
    public Collection<TodoItemTask> findByAssignedStatus(boolean status) {
        List<TodoItemTask> list = todoItemTasks.stream()
                .filter(s -> s.isAssigned() == status)
                .toList();

        return list;
    }

    @Override
    public Collection<TodoItemTask> findByPersonId(int personId) {

        return todoItemTasks.stream()
                .filter(s -> s.getAssignee() != null)
                .filter(s -> s.getAssignee().getId() == personId)
                .toList();
    }

    @Override
    public void remove(int id) {
        todoItemTasks.removeIf(s -> s.getId() == id);
    }

    public static TodoItemTaskDAOCollection getInstance() {
        if (todoItemTaskDAOCollection == null) {
            todoItemTaskDAOCollection = new TodoItemTaskDAOCollection();
        }
        return todoItemTaskDAOCollection;
    }

    public void loadTodoItemTasks() {

        try (FileReader reader = new FileReader(file)) {

            todoItemTasks = mapper.readValue(reader, new TypeReference<List<TodoItemTask>>() {});

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTodoItemTasks() {
        try (FileWriter writer = new FileWriter(file)) {

            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            writer.write(mapper.writeValueAsString(todoItemTasks));

            todoItemTasks.clear();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
