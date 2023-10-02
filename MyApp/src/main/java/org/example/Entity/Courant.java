package org.example.Entity;

import org.example.Enums.CompteEtat;

import java.time.LocalDate;

public class Courant extends Compte {
    private Double decouvert;

    public Courant(String numero, int solde, LocalDate date, CompteEtat compteEtat, Client client,Double decouvert) {
        super(numero, solde, date, compteEtat, client);
        setDecouvert(decouvert);
    }

    public Double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(Double decouvert) {
        this.decouvert = decouvert;
    }
}
