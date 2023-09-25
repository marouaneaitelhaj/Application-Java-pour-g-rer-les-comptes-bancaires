package org.example.Entity;

import org.example.Enums.CompteEtat;

import java.time.LocalDate;

public class Epargne extends Compte {
    private Double tauxDinteret;

    public Epargne(Compte compte, Double tauxDinteret) {
        super(compte.getNumero(), compte.getSolde(), compte.getDate(), compte.getCompteEtat(), compte.getClient());
        this.tauxDinteret = tauxDinteret;
    }

    public Double getTauxDinteret() {
        return tauxDinteret;
    }

    public void setTauxDinteret(Double tauxDinteret) {
        this.tauxDinteret = tauxDinteret;
    }
}
