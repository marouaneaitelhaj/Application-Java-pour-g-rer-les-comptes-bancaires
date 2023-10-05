package org.example.Entity;

import java.util.Date;

public class EmployeAgenceLogs {
    private Employe employe;
    private Agence agence;
    private Date date;

    public EmployeAgenceLogs(Employe employe, Agence agence, Date date) {
        this.employe = employe;
        this.agence = agence;
        this.date = date;
    }
    public EmployeAgenceLogs() {
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
