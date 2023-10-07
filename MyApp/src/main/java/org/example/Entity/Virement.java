package org.example.Entity;

import java.time.LocalDate;

public class Virement {
    private Compte CompteEmetteur;

    private Compte CompteDestinataire;
    private int mantant;
    private LocalDate date;

    public Virement(Compte compteEmetteur, Compte compteDestinataire, int mantant,LocalDate date) {
        CompteEmetteur = compteEmetteur;
        CompteDestinataire = compteDestinataire;
        this.mantant = mantant;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Compte getCompteEmetteur() {
        return CompteEmetteur;
    }

    public void setCompteEmetteur(Compte compteEmetteur) {
        CompteEmetteur = compteEmetteur;
    }

    public Compte getCompteDestinataire() {
        return CompteDestinataire;
    }

    public void setCompteDestinataire(Compte compteDestinataire) {
        CompteDestinataire = compteDestinataire;
    }

    public int getMantant() {
        return mantant;
    }

    public void setMantant(int mantant) {
        this.mantant = mantant;
    }
}
