package org.fyr.DAO;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.fyr.model.Person;

import java.io.*;
import java.util.*;

//TODO -- • Use the Stream API in implementing DAO classes
public class PersonDaoCollection implements PersonDAO, Serializable {

    private static PersonDaoCollection personDaoCollection;

    private ObjectMapper mapper = new ObjectMapper();


    private final File file = new File("src/main/resources/protocols/Person.json");

    private List<Person> personList;

    private PersonDaoCollection() {
        personList = new ArrayList<>();
    }

    @Override
    public Person persist(Person person) {
        // TODO add check for email if exists, also
        if(personList.contains(person)) {
            return person;
        }
        personList.add(person);

        return person;
    }

    @Override
    public Person findById(int id) {

        Optional<Person> p = personList.stream()
                .filter(s -> s.getId() == id)
                .findFirst();

        return p.get();
    }

    @Override
    public Person findByEmail(String email) {
        Optional<Person> p = personList.stream()
                .filter(s -> s.getEmail().equalsIgnoreCase(email))
                .findFirst();

        return p.get();
    }

    @Override
    public Collection<Person> findAll() {
        return personList;
    }

    @Override
    public void remove(int id) {
        personList.removeIf(s -> s.getId() == id);

    }

    public static PersonDaoCollection getInstance() {
        if (personDaoCollection == null) {
            personDaoCollection = new PersonDaoCollection();
        }
        return personDaoCollection;
    }

    public void loadPersons(){

        //TODO -- Rensa upp kommentarer.

        mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        try(FileReader reader = new FileReader(file)){

            personList = mapper.readValue(reader, new TypeReference<List<Person>>(){});

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void savePersons(){
        System.out.println("--------------------------------------\nSave Persons method\n--------------------------------------");
        try(FileWriter writer = new FileWriter(file)) {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            writer.write(mapper.writeValueAsString(personList).toString());

            personList.clear();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
