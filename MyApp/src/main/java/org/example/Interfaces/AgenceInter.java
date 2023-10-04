package org.example.Interfaces;

import org.example.Entity.Agence;
import org.example.Repository.CrudRepository;

import java.util.Optional;

public interface AgenceInter extends CrudRepository<Agence> {
    Optional<Agence> findOneByAdresse(Agence agence);
}
