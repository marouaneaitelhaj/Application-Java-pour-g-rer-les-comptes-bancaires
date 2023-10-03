package org.example.Entity;

import java.time.LocalDate;

public class Operation {
    private int numero;
    private LocalDate dateDeCreation;
    private int montant;
    private Compte compte;
    private Employe employe;

    public Operation(int numero, LocalDate dateDeCreation, int montant, Compte compte, Employe employe) {
        this.numero = numero;
        this.dateDeCreation = dateDeCreation;
        this.montant = montant;
        this.compte = compte;
        this.employe = employe;
    }
    public Operation() {

    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(LocalDate dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}
