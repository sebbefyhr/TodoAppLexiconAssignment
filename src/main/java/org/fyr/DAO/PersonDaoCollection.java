package org.fyr.DAO;

import org.fyr.model.Person;

import java.util.Collection;
import java.util.List;

public class PersonDaoCollection implements PersonDAO{




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
}
