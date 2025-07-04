package org.fyr.DAO;

import org.fyr.model.AppUser;

import java.util.Collection;
import java.util.List;

public class AppUserDaoCollection implements AppUserDAO{


    @Override
    public AppUser persist(AppUser appUser) {
        return null;
    }

    @Override
    public AppUser findByUsername(String username) {
        return null;
    }

    @Override
    public Collection<AppUser> findAll() {
        return List.of();
    }

    @Override
    public void remove(String username) {

    }
}
