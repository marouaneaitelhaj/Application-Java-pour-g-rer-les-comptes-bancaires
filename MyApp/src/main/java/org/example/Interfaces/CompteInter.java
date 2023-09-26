package org.example.Interfaces;

import org.example.Entity.Compte;
import org.example.Repository.CrudRepository;

import java.util.List;

public interface CompteInter extends CrudRepository<Compte> {
    List<Compte> findByClient(Compte compte);
}
