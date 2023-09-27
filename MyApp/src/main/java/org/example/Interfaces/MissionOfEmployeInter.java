package org.example.Interfaces;

import org.example.Entity.Employe;
import org.example.Entity.MissionOfEmploye;
import org.example.Implementations.MissionOfEmployeImpl;
import org.example.Repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface MissionOfEmployeInter extends CrudRepository<MissionOfEmploye> {
    Optional<HashMap<String, Integer>> EmployeStatistiques();

    Optional<HashMap<String, Integer>> MissionStatistiques();

    Optional<List<MissionOfEmploye>> findByEmploye(Employe employe);
}
