package org.example.Interfaces;

import org.example.Entity.Employe;
import org.example.Repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeInter extends CrudRepository<Employe> {
    Optional<List<Employe>> findByAtr(String text);
}
