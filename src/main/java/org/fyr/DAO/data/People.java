package org.fyr.DAO.data;

import java.util.Collection;

public interface People {
    PersonNew create (PersonNew p);
    Collection<PersonNew> findAll();
    PersonNew findById(int id);
    Collection<PersonNew> findByName(String name);
    PersonNew update(PersonNew p);
    boolean deleteById(int id);
}
