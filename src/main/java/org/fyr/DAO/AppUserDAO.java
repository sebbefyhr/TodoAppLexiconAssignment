package org.fyr.DAO;

import org.fyr.model.AppUser;

import java.util.Collection;

public interface AppUserDAO<T> {

    AppUser persist(AppUser appUser);
    AppUser findByUsername(String username);
    Collection<AppUser> findAll();
    void remove(String username);

}
