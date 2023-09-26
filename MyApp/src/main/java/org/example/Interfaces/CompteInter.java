package org.example.Interfaces;

import org.example.Entity.Compte;
import org.example.Repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompteInter extends CrudRepository<Compte> {
    List<Compte> findByClient(Compte compte);

    Optional<List<Compte>> findAllByStatus();

    Optional<List<Compte>> findAllByDate();
}
