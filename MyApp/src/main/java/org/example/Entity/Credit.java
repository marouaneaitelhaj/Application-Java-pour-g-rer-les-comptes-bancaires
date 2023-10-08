package org.example.Entity;

import org.example.Enums.CreditEtat;

import java.time.LocalDate;
import java.util.Date;

public class Credit {
    private String numero;
    private Client client;
    public static final double TAUX = 0.12;
    private Agence agence;
    private LocalDate date;
    private int montant;
    private int duree;
    private String remarques;
    private CreditEtat creditEtat;

    public Credit(String numero, Client client, Agence agence, LocalDate date, int montant, int duree, String remarques, CreditEtat creditEtat) {
        this.numero = numero;
        this.client = client;
        this.agence = agence;
        this.date = date;
        this.montant = montant;
        this.duree = duree;
        this.remarques = remarques;
        this.creditEtat = creditEtat;
    }
    public Credit(){

    }

    public Credit(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getRemarques() {
        return remarques;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    public CreditEtat getCreditEtat() {
        return creditEtat;
    }

    public void setCreditEtat(CreditEtat creditEtat) {
        this.creditEtat = creditEtat;
    }
}
