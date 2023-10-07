package org.example.Entity;

import org.example.Enums.AffectationStatus;

import java.lang.ref.PhantomReference;
import java.time.LocalDate;
import java.util.Date;

public class AgenceOfEmploye {
    private Employe employe;
    private Agence agence;
    private LocalDate date;
    private AffectationStatus affectationStatus;

    public AffectationStatus getAffectationStatus() {
        return affectationStatus;
    }

    public void setAffectationStatus(AffectationStatus affectationStatus) {
        this.affectationStatus = affectationStatus;
    }

    public AgenceOfEmploye(Employe employe, Agence agence, LocalDate date, AffectationStatus affectationStatus) {
        this.employe = employe;
        this.agence = agence;
        this.date = date;
        this.affectationStatus = affectationStatus;
    }
    public AgenceOfEmploye() {
    }

    public AgenceOfEmploye(Employe employe, Agence agence,AffectationStatus affectationStatus) {
        this.employe = employe;
        this.agence = agence;
        this.affectationStatus = affectationStatus;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
