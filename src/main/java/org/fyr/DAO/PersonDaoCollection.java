package org.fyr.DAO;

import org.fyr.model.Person;

import java.util.Collection;
import java.util.List;

//TODO -- • Use the Stream API in implementing DAO classes
public class PersonDaoCollection implements PersonDAO{

    private static PersonDaoCollection personDaoCollection;

    private PersonDaoCollection(){}

    @Override
    public Person persist(Person person) {

        return null;
    }

    @Override
    public Person findById(int id) {
        return null;
    }

    @Override
    public Person findByEmail(String email) {
        return null;
    }

    @Override
    public Collection<Person> findAll() {
        return List.of();
    }

    @Override
    public void remove(int id) {

    }

    public static PersonDaoCollection getInstance(){
        if(personDaoCollection == null){
            personDaoCollection = new PersonDaoCollection();
        }
        return personDaoCollection;
    }
}
