package org.fyr.DAO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.fyr.model.TodoItem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//TODO -- • Use the Stream API in implementing DAO classes
public class TodoItemDaoCollection implements TodoItemDAO {

    private static TodoItemDaoCollection todoItemDaoCollection;
    private final String file = "src/main/resources/protocols/TodoItem.json";
    private List<TodoItem> todoItems;
    private ObjectMapper mapper = new ObjectMapper();

    private TodoItemDaoCollection() {
        this.todoItems = new ArrayList<>();
    }

    @Override
    public TodoItem persist(TodoItem todoItem) {
        if (todoItems.contains(todoItem)) {
            return todoItem;
        }
        todoItems.add(todoItem);
        return todoItem;
    }

    @Override
    public TodoItem findById(int id) {

        return todoItems.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .get();
    }

    @Override
    public Collection<TodoItem> findAll() {

        return todoItems;
    }

    @Override
    public Collection<TodoItem> findAllByDoneStatus(boolean done) {
        return todoItems.stream()
                .filter(s -> s.isDone() == done)
                .toList();
    }

    @Override
    public Collection<TodoItem> findByTitleContains(String title) {
        return todoItems.stream()
                .filter(s -> s.getTitle().equalsIgnoreCase(title))
                .toList();
    }

    @Override
    public Collection<TodoItem> findByPersonId(int personId) {

        return todoItems.stream()
                .filter(s -> s.getCreator().getId() == personId)
                .toList();
    }

    @Override
    public Collection<TodoItem> findByDeadlineBefore(LocalDate date) {
        return todoItems.stream()
                .filter(s -> s.getDeadLine().isBefore(date))
                .toList();
    }

    @Override
    public Collection<TodoItem> findByDeadlineAfter(LocalDate date) {
        return todoItems.stream()
                .filter(s -> s.getDeadLine().isAfter(date))
                .toList();
    }

    @Override
    public void remove(int id) {
        todoItems.removeIf(s -> s.getId() == id);
    }

    public static TodoItemDaoCollection getInstance() {
        if (todoItemDaoCollection == null) {
            todoItemDaoCollection = new TodoItemDaoCollection();
        }
        return todoItemDaoCollection;
    }

    public void loadTodoItems() {

        try (FileReader reader = new FileReader(file)) {

            todoItems = mapper.readValue(reader, new TypeReference<List<TodoItem>>() {
            });

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTodoItems() {

        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try (FileWriter writer = new FileWriter(file)) {

            writer.write(mapper.writeValueAsString(todoItems));

            todoItems.clear();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
