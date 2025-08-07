package org.fyr.DAO;

import java.util.Collection;

public interface GeneralDao<T> {

    //TODO -- Keep this general DAO or just let each interface use these two methods in themselves?
    // it seems redundant to have this interface for just these two methods?
    // ask simon

    T persist(T type);
    Collection<T> findAll();
}
