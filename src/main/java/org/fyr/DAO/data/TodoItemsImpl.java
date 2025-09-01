package org.fyr.DAO.data;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoItemsImpl implements TodoItems{

    private Connection conn;

    public TodoItemsImpl(Connection connection) {
        try {
            this.conn = connection;
        } catch (NullPointerException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Todo create(Todo todo) {

        try{
            PreparedStatement createTodo = conn.prepareStatement("insert into todo_item values(?,?,?,?,?,?");

            createTodo.setInt(1, todo.getId());
            createTodo.setString(2, todo.getTitle());
            createTodo.setString(3, todo.getDescription());
            createTodo.setDate(4, Date.valueOf(todo.getDeadline()));
            createTodo.setBoolean(5, todo.isDone());
            createTodo.setInt(6, todo.getAssignee_id());

            createTodo.executeUpdate();

            return todo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Todo> findAll() {
        List<Todo> list = new ArrayList<>();

        try{
            Statement findAll = conn.createStatement();
            String state = "select * from todo_item";

            ResultSet rs = findAll.executeQuery(state);

            while(rs.next()){
                Todo todo = new Todo(rs.getInt("todo_id"), rs.getString("title"), rs.getString("description")
                ,rs.getDate("deadline").toLocalDate(), rs.getBoolean("done"), rs.getInt("assignee_id"));

                list.add(todo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public Todo findById(int id) {

        Todo t = null;
        try{
            PreparedStatement findById = conn.prepareStatement("select * from todo_item where todo_id = ?");
            findById.setInt(1, id);

            ResultSet rs = findById.executeQuery();

            while(rs.next()){
                t = new Todo(rs.getInt("todo_id"), rs.getString("title"), rs.getString("description")
                        ,rs.getDate("deadline").toLocalDate(), rs.getBoolean("done"), rs.getInt("assignee_id"));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return t;
    }

    @Override
    public Collection<Todo> findByDoneStatus(boolean status) {
        List<Todo> list = new ArrayList<>();

        try{
            PreparedStatement findByDone = conn.prepareStatement("select * from todo_item where done = ?");
            findByDone.setBoolean(1, status);

            ResultSet rs = findByDone.executeQuery();

            while(rs.next()){
                Todo todo = new Todo(rs.getInt("todo_id"), rs.getString("title"), rs.getString("description")
                        ,rs.getDate("deadline").toLocalDate(), rs.getBoolean("done"), rs.getInt("assignee_id"));

                list.add(todo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public Collection<Todo> findByAssignee(int id) {
        List<Todo> list = new ArrayList<>();

        try{
            PreparedStatement findByDone = conn.prepareStatement("select * from todo_item where assignee_id = ?");
            findByDone.setInt(1, id);

            ResultSet rs = findByDone.executeQuery();

            while(rs.next()){
                Todo todo = new Todo(rs.getInt("todo_id"), rs.getString("title"), rs.getString("description")
                        ,rs.getDate("deadline").toLocalDate(), rs.getBoolean("done"), rs.getInt("assignee_id"));

                list.add(todo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public Collection<Todo> findByAssignee(PersonNew person) {

        /*
        //TODO -- fel att göra så här?

        List<Todo> list = (List<Todo>) findByAssignee(person.getId());
         */

        List<Todo> list = new ArrayList<>();
        try{
            PreparedStatement stmnt = conn.prepareStatement("select * from todo_item where assignee_id = " +
                    "(select person_id from person where person_id = ? and first_name = ? and last_name = ?)");

            stmnt.setInt(1, person.getId());
            stmnt.setString(2, person.getFirstName());
            stmnt.setString(3, person.getLastName());

            ResultSet rs = stmnt.executeQuery();

            while(rs.next()){
                Todo t = new Todo(rs.getInt("todo_id"), rs.getString("title"), rs.getString("description"),
                        rs.getDate("deadline").toLocalDate(), rs.getBoolean("done"), rs.getInt("assignee_id"));

                list.add(t);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return list;
    }

    @Override
    public Collection<Todo> findByUnassignedTodoItems() {
        List<Todo> list = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            String s = "select * from todo_item where assignee_id is null";

            ResultSet rs = statement.executeQuery(s);

            while(rs.next()){
                Todo todo = new Todo(rs.getInt("todo_id"), rs.getString("title"), rs.getString("description")
                        ,rs.getDate("deadline").toLocalDate(), rs.getBoolean("done"), rs.getInt("assignee_id"));

                list.add(todo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public Todo update(Todo todo) {

        try{
            PreparedStatement updateTodo =
                    conn.prepareStatement("update todo_item set title = ?, description = ?, deadline =?, " +
                            "done =?, assignee_id =? where todo_id = ?");

            updateTodo.setString(1, todo.getTitle());
            updateTodo.setString(2, todo.getDescription());
            updateTodo.setDate(3, Date.valueOf(todo.getDeadline()));
            updateTodo.setBoolean(4, todo.isDone());
            updateTodo.setInt(5, todo.getAssignee_id());

            int res = updateTodo.executeUpdate();

            if (res == 1) return todo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean deleteById(int id) {

        try{
            PreparedStatement deleteById = conn.prepareStatement("delete from todo_item where id = ?");
            deleteById.setInt(1, id);

            int success  = deleteById.executeUpdate();

            return success == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
