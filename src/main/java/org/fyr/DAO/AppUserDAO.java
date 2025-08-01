package org.fyr.DAO;

import org.fyr.model.AppUser;

import java.util.Collection;

public interface AppUserDAO<T> {

    T persist(T appUser);
    T findByUsername(String username);
    Collection<T> findAll();
    void remove(String username);

}
