package org.carrental.dao;

import java.util.List;

                                 /**COMMON METHOD FOR EACH TABLE
                                  INSERT/ GET ALL / GET BY ID / UPDATE /DELETE BY ID
                                WE MADE IT GENERIC (HERE T IS TYPE)*/
public interface ICrud<T> {

                                           // INSERT
    void insert(T obj);

                                            // GET ALL
    List<T> getAll();

                                           // getById
    T GetById (Long id);

                                            // UPDATE
    void Update(T obj , Long id);

                                             //DELETE BY ID
    void  DeleteById(Long id);


}
