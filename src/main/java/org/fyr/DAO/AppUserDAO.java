package org.fyr.DAO;

import java.util.Collection;

public interface AppUserDAO<T> extends GeneralDao<T> {

    T findByUsername(String username);
    void remove(String username);

}
