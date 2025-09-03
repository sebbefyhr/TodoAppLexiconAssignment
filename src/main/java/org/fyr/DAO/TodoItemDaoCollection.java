package org.fyr.DAO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.fyr.model.Person;
import org.fyr.model.TodoItem;

import java.awt.desktop.OpenFilesEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class TodoItemDaoCollection implements TodoItemDAO {

    private Connection conn;


    //TODO -- keep thses as we now save to db?
    private static TodoItemDaoCollection todoItemDaoCollection;
    private final String file = "src/main/resources/protocols/TodoItem.json";
    private List<TodoItem> todoItems;
    private ObjectMapper mapper = new ObjectMapper();

    private TodoItemDaoCollection(Connection conn) {
        this.conn = conn;
        this.todoItems = new ArrayList<>();
    }

    @Override
    public TodoItem create(TodoItem todo) {
        if (todo == null) return null;

        try (PreparedStatement createTodo =
                     conn.prepareStatement("insert into todo_item(title, description, deadline, done, assignee_id )" +
                                     " values(?,?,?,?,?)",
                             Statement.RETURN_GENERATED_KEYS)
        ) {

            createTodo.setString(1, todo.getTitle());
            createTodo.setString(2, todo.getDescription());
            createTodo.setDate(3, Date.valueOf(todo.getDeadline()));
            createTodo.setBoolean(4, todo.isDone());
            createTodo.setInt(5, todo.getAssignee_id());

            int row = createTodo.executeUpdate();

            if (row == 1) {
                try (ResultSet key = createTodo.getGeneratedKeys()) {
                    if (key.next()) {
                        todo.setId(key.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return todo;
    }

    @Override
    public Collection<TodoItem> findAll() {
        List<TodoItem> list = new ArrayList<>();
        String stmnt = "select * from todo_item";


        try (Statement findAll = conn.createStatement();
             ResultSet rs = findAll.executeQuery(stmnt)) {

            while (rs.next()) {
                list.add(new TodoItem(rs.getInt(1), rs.getString(2), rs.getString(3)
                        , rs.getDate(4).toLocalDate(), rs.getBoolean(5), rs.getInt(6)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public TodoItem findById(int id) {

        try (PreparedStatement findById = conn.prepareStatement("select * from todo_item where todo_id = ?")) {
            findById.setInt(1, id);

            ResultSet rs = findById.executeQuery();

            while (rs.next()) {
                return new TodoItem(rs.getInt(1), rs.getString(2), rs.getString(3)
                        , rs.getDate(4).toLocalDate(), rs.getBoolean(5), rs.getInt(6));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //TODO -- throw exception instead of returning null
        return null;
    }

    @Override
    public Collection<TodoItem> findByDoneStatus(boolean status) {
        List<TodoItem> list = new ArrayList<>();

        try {
            PreparedStatement findByDone = conn.prepareStatement("select * from todo_item where done = ?");
            findByDone.setBoolean(1, status);

            ResultSet rs = findByDone.executeQuery();

            while (rs.next()) {
                list.add(new TodoItem(rs.getInt(1), rs.getString(2), rs.getString(3)
                        , rs.getDate(4).toLocalDate(), rs.getBoolean(5), rs.getInt(6)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public Collection<TodoItem> findByAssignee(int id) {
        List<TodoItem> list = new ArrayList<>();

        try (PreparedStatement findByDone = conn.prepareStatement("select * from todo_item where assignee_id = ?")
        ) {

            findByDone.setInt(1, id);

            ResultSet rs = findByDone.executeQuery();

            while (rs.next()) {
                list.add(new TodoItem(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getDate(4).toLocalDate(), rs.getBoolean(5), rs.getInt(6)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public Collection<TodoItem> findByAssignee(Person person) {

        List<TodoItem> list = new ArrayList<>();

        /*
        //TODO -- fel att göra så här?
        List<TodoItem> list = (List<TodoItem>) findByAssignee(person.getId());
         */

        String selectString = "select * from todo_item where assignee_id = " +
                "(select person_id from person where person_id = ? and first_name = ? and last_name = ?)";

        try (PreparedStatement stmnt = conn.prepareStatement(selectString)) {

            stmnt.setInt(1, person.getId());
            stmnt.setString(2, person.getFirstName());
            stmnt.setString(3, person.getLastName());

            ResultSet rs = stmnt.executeQuery();

            while (rs.next()) {
                list.add(new TodoItem(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getDate(4).toLocalDate(), rs.getBoolean(5), rs.getInt(6)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Collection<TodoItem> findByUnassignedTodoItems() {
        List<TodoItem> list = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            String s = "select * from todo_item where assignee_id is null";

            ResultSet rs = statement.executeQuery(s);

            while (rs.next()) {
                list.add(new TodoItem(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getDate(4).toLocalDate(), rs.getBoolean(5), rs.getInt(6)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public TodoItem update(TodoItem todo) {

        String stmnt = "update todo_item set title = ?, description = ?, deadline =?, " +
                "done =?, assignee_id =? where todo_id = ?";

        try (PreparedStatement updateTodo = conn.prepareStatement(stmnt)) {

            updateTodo.setString(1, todo.getTitle());
            updateTodo.setString(2, todo.getDescription());
            updateTodo.setDate(3, Date.valueOf(todo.getDeadline()));
            updateTodo.setBoolean(4, todo.isDone());
            updateTodo.setInt(5, todo.getAssignee_id());

            updateTodo.setInt(6, todo.getId());

            int res = updateTodo.executeUpdate();

            if (res > 0) {

                //TODO -- works as Should! returns as should?? fråga simon om return value.
                return todo;
                //return new TodoItem(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4).toLocalDate(), rs.getBoolean(5), rs.getInt(6));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean deleteById(int id) {
        String stmnt = "delete from todo_item where todo_id = ?";

        try (PreparedStatement deleteById = conn.prepareStatement(stmnt)) {

            deleteById.setInt(1, id);

            int success = deleteById.executeUpdate();

            return success == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static TodoItemDaoCollection getInstance(Connection conn) {
        if (todoItemDaoCollection == null) {
            todoItemDaoCollection = new TodoItemDaoCollection(conn);
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
