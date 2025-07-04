package org.fyr.DAO;

import org.fyr.model.AppUser;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;



//TODO -- • Use the Stream API in implementing DAO classes
public class AppUserDaoCollection implements AppUserDAO<AppUser>, Serializable {

    private static AppUserDaoCollection appUserDaoCollection;

    private AppUserDaoCollection(){}


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

    public static AppUserDaoCollection getInstance(){
        if(appUserDaoCollection == null){
            appUserDaoCollection = new AppUserDaoCollection();
        }
        return appUserDaoCollection;
    }
}
