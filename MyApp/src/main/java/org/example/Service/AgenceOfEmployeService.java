package org.example.Service;

import org.example.Entity.AgenceOfEmploye;
import org.example.Exceptions.AgenceException;
import org.example.Interfaces.AgenceOfEmployeInter;

import java.util.List;
import java.util.Optional;

public class AgenceOfEmployeService implements AgenceOfEmployeInter {
    private final AgenceOfEmployeInter agenceOfEmployeInter;

    public AgenceOfEmployeService(AgenceOfEmployeInter agenceOfEmployeInter) {
        this.agenceOfEmployeInter = agenceOfEmployeInter;
    }


    public Optional<AgenceOfEmploye> save(AgenceOfEmploye agenceOfEmploye) throws AgenceException {
        if (agenceOfEmploye.getAgence().getCode().isEmpty() || agenceOfEmploye.getEmploye().getMatricule().isEmpty())
            throw new AgenceException("Le champ de employe ou agence est vide");
        return this.agenceOfEmployeInter.save(agenceOfEmploye);
    }


    public Optional<AgenceOfEmploye> update(AgenceOfEmploye agenceOfEmploye) {
        return Optional.empty();
    }


    public int delete(AgenceOfEmploye agenceOfEmploye) {
        return 0;
    }


    public Optional<AgenceOfEmploye> findOne(AgenceOfEmploye agenceOfEmploye) {
        return Optional.empty();
    }


    public Optional<List<AgenceOfEmploye>> findAll() {
        return Optional.empty();
    }
}
