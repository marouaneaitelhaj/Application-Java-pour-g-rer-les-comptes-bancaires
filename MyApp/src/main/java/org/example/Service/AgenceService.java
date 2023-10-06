package org.example.Service;

import org.example.Entity.Agence;
import org.example.Exceptions.AgenceException;
import org.example.Interfaces.AgenceInter;

import java.util.Optional;

public class AgenceService {
    private final AgenceInter agenceImpl;

    public AgenceService(AgenceInter agenceImpl) {
        this.agenceImpl = agenceImpl;
    }

    public Optional<Agence> findByCode(Agence agence) {
        return agenceImpl.findOne(agence);
    }

    public int delete(Agence agence) {
        return this.agenceImpl.delete(agence);
    }

    public Optional<Agence> findByAdresse(Agence agence) {
        return agenceImpl.findOneByAdresse(agence);
    }

    public Optional<Agence> update(Agence agence) throws AgenceException {
        if (agence.getCode().isEmpty()){
            throw new AgenceException("Le champ de code est vide");
        }else {
            return this.agenceImpl.update(agence);
        }
    }

    public void findByEmploye() {
    }

    public Optional<Agence> save(Agence agence) {
        return this.agenceImpl.save(agence);
    }
}
