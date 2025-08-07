package org.fyr.DAO;

import org.fyr.model.Person;

import java.util.Collection;

public interface PersonDAO<T> extends GeneralDao<T>{

    T findById(int id);
    T findByEmail(String email);
    void remove(int id);
}
