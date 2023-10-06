package org.example.Entity;

import java.util.Date;

public class AgenceOfEmploye {
    private Employe employe;
    private Agence agence;
    private Date date;

    public AgenceOfEmploye(Employe employe, Agence agence, Date date) {
        this.employe = employe;
        this.agence = agence;
        this.date = date;
    }
    public AgenceOfEmploye() {
    }

    public AgenceOfEmploye(Employe employe, Agence agence) {
        this.employe = employe;
        this.agence = agence;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
