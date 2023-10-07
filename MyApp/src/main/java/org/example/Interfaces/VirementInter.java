package org.example.Interfaces;

import org.example.Entity.Virement;
import org.example.Repository.CrudRepository;

import java.util.List;

public interface VirementInter extends CrudRepository<Virement> {
    List<Virement> findAllByDate();
}
