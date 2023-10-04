package org.example.Service;

import org.example.Entity.Agence;
import org.example.Implementations.AgenceImpl;
import org.example.Views.AgenceView;

import java.util.Optional;

public class AgenceService {
    private AgenceImpl agenceImpl;

    public AgenceService(AgenceImpl agenceImpl) {
        this.agenceImpl = agenceImpl;
    }

    private void findByCode() {
    }

    private void delete() {
    }

    private void findByAdresse() {
    }

    private void update() {
    }

    private void findByEmploye() {
    }

    public boolean save(Agence agence) {
        Optional<Agence> agenceOptional = this.agenceImpl.save(agence);
        return agenceOptional.isPresent();
    }
}
