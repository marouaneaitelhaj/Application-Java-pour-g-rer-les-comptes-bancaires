package org.example.Entity;

import java.util.List;

public class Agence {
    private String code;
    private String nom;
    private String adresse;
    private Employe employe;
    private String numeroTelephone;
    private List<EmployeAgenceLogs> employeAgenceLogs;

    public Agence(String code, String nom, String adresse, String numeroTelephone, Employe employe) {
        this.code = code;
        this.nom = nom;
        this.adresse = adresse;
        this.numeroTelephone = numeroTelephone;
        this.employe = employe;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public List<EmployeAgenceLogs> getEmployeAgenceLogs() {
        return employeAgenceLogs;
    }

    public void setEmployeAgenceLogs(List<EmployeAgenceLogs> employeAgenceLogs) {
        this.employeAgenceLogs = employeAgenceLogs;
    }

    public Agence() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }
}
