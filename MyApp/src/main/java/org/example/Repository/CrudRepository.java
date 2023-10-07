package org.example.Repository;

import org.example.Exceptions.AgenceException;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<M> {
    Optional<M> save(M m) throws AgenceException;

    Optional<M> update(M m) throws AgenceException;

    int delete(M m);

    Optional<M> findOne(M m);

    Optional<List<M>> findAll();
}