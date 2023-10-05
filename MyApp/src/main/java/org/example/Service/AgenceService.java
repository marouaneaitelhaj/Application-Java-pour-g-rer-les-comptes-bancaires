package org.example.Service;

import org.example.Entity.Agence;
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

    public void findByAdresse() {
    }

    public void update() {
    }

    public void findByEmploye() {
    }

    public Optional<Agence> save(Agence agence) {
        return this.agenceImpl.save(agence);
    }
}
