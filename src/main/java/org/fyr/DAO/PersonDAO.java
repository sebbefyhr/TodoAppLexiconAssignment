package org.fyr.DAO;

import org.fyr.model.Person;

import java.util.Collection;

public interface PersonDAO {
    Person create (Person p);
    Collection<Person> findAll();
    Person findById(int id);
    Collection<Person> findByName(String name);
    Person update(Person p);
    boolean deleteById(int id);
}
