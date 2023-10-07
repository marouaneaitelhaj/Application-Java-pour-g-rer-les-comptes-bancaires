package org.example.Entity;

import org.example.Enums.CompteEtat;

import java.time.LocalDate;

public class Compte {
    private String numero;
    private int solde;
    private LocalDate date;
    private CompteEtat compteEtat;
    private Client client;
    private Agence agence;

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Compte(Agence agence) {
        this.agence = agence;
    }

    public Compte(String numero, int solde, LocalDate date, CompteEtat compteEtat, Client client) {
        this.numero = numero;
        this.solde = solde;
        this.date = date;
        this.compteEtat = compteEtat;
        this.client = client;
    }

    public Compte(String numero, int solde, LocalDate date, CompteEtat compteEtat, Client client, Agence agence) {
        this.numero = numero;
        this.solde = solde;
        this.date = date;
        this.compteEtat = compteEtat;
        this.client = client;
        this.agence = agence;
    }

    public Compte() {

    }

    public Compte(String numero) {
        this.numero = numero;
    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CompteEtat getCompteEtat() {
        return compteEtat;
    }

    public void setCompteEtat(CompteEtat compteEtat) {
        this.compteEtat = compteEtat;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
