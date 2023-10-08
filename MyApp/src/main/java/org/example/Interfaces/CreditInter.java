package org.example.Interfaces;

import org.example.Entity.Credit;
import org.example.Repository.CrudRepository;

import java.util.List;

public interface CreditInter extends CrudRepository<Credit> {
    List<Credit> findAllBystatus();

    List<Credit> findAllByDate();
}
