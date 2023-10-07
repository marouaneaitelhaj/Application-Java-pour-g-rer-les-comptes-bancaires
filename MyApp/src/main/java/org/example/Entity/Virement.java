package org.example.Entity;

public class Virement {
    private Compte CompteEmetteur;

    private Compte CompteDestinataire;
    private int mantant;

    public Virement(Compte compteEmetteur, Compte compteDestinataire, int mantant) {
        CompteEmetteur = compteEmetteur;
        CompteDestinataire = compteDestinataire;
        this.mantant = mantant;
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
