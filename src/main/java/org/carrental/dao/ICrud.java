package org.carrental.dao;

import java.util.List;

public interface ICrud<T> {

    void insert(T obj);
    List<T> getAll();
    T GetById (Long id);
    T Update(T obj , Long id);
    void  DeleteById(Long id);


}
