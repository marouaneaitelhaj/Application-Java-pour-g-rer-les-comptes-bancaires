package org.example.Repository;

import java.util.List;

public interface CrudRepository<M> {
    M save(M t);
    M update(M t);
    int delete(M t);
    M findOne(M t);
    List<M> findAll();
}
