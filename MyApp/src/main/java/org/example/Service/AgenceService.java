package org.example.Service;

import org.example.Entity.Agence;
import org.example.Entity.Employe;
import org.example.Exceptions.MyException;
import org.example.Interfaces.AgenceInter;

import java.util.List;
import java.util.Optional;

public class AgenceService {
    private final AgenceInter agenceImpl;

    public AgenceService(AgenceInter agenceImpl) {
        this.agenceImpl = agenceImpl;
    }


    public Optional<Agence> findByCode(Agence agence) throws MyException {
        if (agence.getCode().isEmpty())
            throw new MyException("Le champ de code est vide");
        return agenceImpl.findOne(agence);
    }

    public int delete(Agence agence) throws MyException {
        if (agence.getCode().isEmpty())
            throw new MyException("Le champ de code est vide");
        return this.agenceImpl.delete(agence);
    }

    public Optional<Agence> findByAdresse(Agence agence) throws MyException {
        if (agence.getAdresse().isEmpty())
            throw new MyException("Le champ de adresse est vide");
        return agenceImpl.findOneByAdresse(agence);
    }

    public Optional<Agence> update(Agence agence) throws MyException {
        if (agence.getCode().isEmpty()) {
            throw new MyException("Le champ de code est vide");
        } else {
            return this.agenceImpl.update(agence);
        }
    }
    public List<Agence> findByEmploye(Employe employe) {
        return this.agenceImpl.findByEmploye(employe);
    }

    public Optional<Agence> save(Agence agence) throws MyException {
        if (agence.getCode().isEmpty())
            throw new MyException("Le champ de code est vide");
        return this.agenceImpl.save(agence);
    }
    public List<Agence> contactService(){
        return this.agenceImpl.contact();
    }
}
