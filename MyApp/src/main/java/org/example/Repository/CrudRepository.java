package org.example.Repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<M> {
    Optional<M> save(M t);
    Optional<M> update(M t);
    int delete(M t);
    Optional<M> findOne(M t);
    List<M> findAll();
}
