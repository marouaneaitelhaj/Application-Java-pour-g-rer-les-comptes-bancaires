package org.example.Service;

import org.example.Entity.Agence;
import org.example.Entity.Compte;
import org.example.Interfaces.CompteAgenceIntre;

public class CompteAgenceService {
    private CompteAgenceIntre compteAgenceIntre;

    public CompteAgenceService(CompteAgenceIntre compteAgenceIntre) {
        this.compteAgenceIntre = compteAgenceIntre;
    }
    public boolean affectCompteAgence(Compte compte){
        return compteAgenceIntre.affectCompteAgence(compte);
    }
}
