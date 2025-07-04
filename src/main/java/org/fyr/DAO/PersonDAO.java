package org.fyr.DAO;

import org.fyr.model.Person;

import java.util.Collection;

public interface PersonDAO {

    Person persist(Person person);
    Person findById(int id);
    Person findByEmail(String email);
    Collection<Person> findAll();
    void remove(int id);
}
