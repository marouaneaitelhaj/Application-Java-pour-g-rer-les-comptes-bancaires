package org.example.Interfaces;

import org.example.Entity.Compte;
import org.example.Repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompteInter extends CrudRepository<Compte> {
    List<Compte> findByClient(Compte compte);

    List<Compte> findAllByStatus();

    List<Compte> findAllByDate();

    Optional<Compte> updateEtat(Compte compte);
}
