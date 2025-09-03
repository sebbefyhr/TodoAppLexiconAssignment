package org.fyr.DAO;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.fyr.model.Person;

import java.io.*;
import java.sql.*;
import java.util.*;

public class PersonDaoCollection implements PersonDAO, Serializable {

    private Connection conn;

    //TODO -- keep thses as we now save to db?
    private static PersonDaoCollection personDaoCollection;
    private ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("src/main/resources/protocols/Person.json");
    private List<Person> personList;


    private PersonDaoCollection(Connection conn) {
        this.conn = conn;
        personList = new ArrayList<>();
    }

    @Override
    public Person create(Person p) {
        if (p == null) return null;

        try {
            PreparedStatement createPersonStatement =
                    conn.prepareStatement("insert into person (first_name, last_name) values(?,?)",
                            Statement.RETURN_GENERATED_KEYS);

            createPersonStatement.setString(1, p.getFirstName());
            createPersonStatement.setString(2, p.getLastName());

            int res = createPersonStatement.executeUpdate();

            if (res == 1) {
                try (ResultSet rs = createPersonStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        p.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    @Override
    public Collection<Person> findAll() {
        List<Person> list = new ArrayList<>();

        String stat = "select * from person";

        try (Statement findAll = conn.createStatement();
             ResultSet rs = findAll.executeQuery(stat)) {

            while (rs.next()) {
                list.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3)));
                // list.add(new Person(rs.getInt("person_id"), rs.getString("first_name"), rs.getString("last_name")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Person findById(int id) {

        String sqlQuery = "select * from person where person_id = ?";

        try (PreparedStatement findById = conn.prepareStatement(sqlQuery)) {

            findById.setInt(1, id);

            ResultSet rs = findById.executeQuery();

            while (rs.next()) {
                // return new Person(rs.getInt("person_id"), rs.getString("first_name"), rs.getString("last_name"));
                return new Person(rs.getInt(1), rs.getString(2), rs.getString(3));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //TODO -- throw exception instead of returning null
        return null;
    }

    @Override
    public Collection<Person> findByName(String name) {

        //TODO -- To Simon, This is my solution after asking you.
        // at first i only checked the first_name but then i added "or last_name"

        if (name.trim().isEmpty() || name == null) return null;

        List<Person> list = new ArrayList<>();

        String stmnt = "select * from person where first_name = ? or last_name = ?";

        try {
            PreparedStatement findByFirstNAme = conn.prepareStatement(stmnt);
            findByFirstNAme.setString(1, name);
            findByFirstNAme.setString(2, name);

            ResultSet rs = findByFirstNAme.executeQuery();

            while (rs.next()) {
                list.add(new Person(rs.getInt("person_id"), rs.getString("first_name"), rs.getString("last_name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Person update(Person person) {

        String updatePersonSttmnt = "update person set first_name = ?, last_name = ? where person_id = ? ";

        try (PreparedStatement updatePerson = conn.prepareStatement(updatePersonSttmnt)){

            updatePerson.setString(1, person.getFirstName());
            updatePerson.setString(2, person.getLastName());
            updatePerson.setInt(3, person.getId());

            int res = updatePerson.executeUpdate();

            //TODO -- works as Should! returns as should?? fråga simon om return value.
            if (res > 0) {
                //ResultSet rs = updatePerson.getResultSet();
                //return new Person(rs.getInt(1), rs.getString(2), rs.getString(3));
                return person;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {

        try (PreparedStatement deleteById = conn.prepareStatement("delete from person where person_id = ?"))
        {
            deleteById.setInt(1, id);

            int val = deleteById.executeUpdate();

            return val == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    //TODO -- Ta bort innan inlämning??
    public static PersonDaoCollection getInstance(Connection connection) {
        if (personDaoCollection == null) {
            personDaoCollection = new PersonDaoCollection(connection);
        }
        return personDaoCollection;
    }

    public void loadPersons() {

        mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);

        try (FileReader reader = new FileReader(file)) {

            personList = mapper.readValue(reader, new TypeReference<List<Person>>() {
            });

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void savePersons() {
        try (FileWriter writer = new FileWriter(file)) {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            writer.write(mapper.writeValueAsString(personList));

            personList.clear();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
