package org.example.Interfaces;

import org.example.Entity.Agence;
import org.example.Entity.Employe;
import org.example.Repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AgenceInter extends CrudRepository<Agence> {
    List<Agence> findByEmploye(Employe employe);

    Optional<Agence> findOneByAdresse(Agence agence);
}
