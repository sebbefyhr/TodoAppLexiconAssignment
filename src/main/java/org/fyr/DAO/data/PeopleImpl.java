package org.fyr.DAO.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PeopleImpl implements People{

    private Connection conn;

    public PeopleImpl(Connection conn){
        try {
            this.conn = conn;
        } catch (NullPointerException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public PersonNew create(PersonNew p) {
        if(p == null) return null;

        try{
            PreparedStatement createPersonStatement = conn.prepareStatement("insert into person values(?,?,?)");

            createPersonStatement.setInt(1, p.getId());
            createPersonStatement.setString(2, p.getFirstName());
            createPersonStatement.setString(3, p.getLastName());

            createPersonStatement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return p;
    }

    @Override
    public Collection<PersonNew> findAll() {
        List<PersonNew> list = new ArrayList<>();

        try{
            Statement findAll = conn.createStatement();
            String stat = "select * from person";

            ResultSet rs = findAll.executeQuery(stat);

            while(rs.next()){
                PersonNew p = new PersonNew(rs.getInt("person_id"), rs.getString("first_name"), rs.getString("last_name"));
                list.add(p);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public PersonNew findById(int id) {
        PersonNew p = null;

        try{
            PreparedStatement findById = conn.prepareStatement("select * from person where person_id = ?");
            findById.setInt(1, id);

            ResultSet rs = findById.executeQuery();

            while(rs.next()) {
                p = new PersonNew(rs.getInt("person_id"), rs.getString("first_name"), rs.getString("last_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return p;
    }

    @Override
    public Collection<PersonNew> findByName(String name) {
        if(name.isEmpty() || name == null) return null;


        List<PersonNew> list = new ArrayList<>();

        try{
            PreparedStatement findByFirstNAme = conn.prepareStatement("select * from person where first_name = ?");
            findByFirstNAme.setString(1, name);

            ResultSet rs = findByFirstNAme.executeQuery();

            while(rs.next()) {
                PersonNew p = new PersonNew(rs.getInt("person_id"), rs.getString("first_name"), rs.getString("last_name"));
                list.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public PersonNew update(PersonNew person) {

        try{
            PreparedStatement updatePerson = conn.prepareStatement("update person set first_name = ?, last_name = ? where person_id = ? ");

            updatePerson.setString(1, person.getFirstName());
            updatePerson.setString(2, person.getLastName());
            updatePerson.setInt(3, person.getId());
            int rs = updatePerson.executeUpdate();

            if(rs == 1) return person;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean deleteById(int id) {

        try{
            PreparedStatement deleteById = conn.prepareStatement("delete from person where person_id = ?");
            deleteById.setInt(1, id);

            int val = deleteById.executeUpdate();

            return val == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
