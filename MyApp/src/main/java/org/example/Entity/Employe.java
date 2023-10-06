package org.example.Entity;

import java.time.LocalDate;
import java.util.List;

public class Employe extends Person {
    private String matricule;
    private LocalDate dateDeRecrutement;
    private String email;
    private List<MissionOfEmploye> missionOfEmployeList;
    private List<AgenceOfEmploye> employeAgenceLogs;

    public Employe(String nom, String prenom, LocalDate dateDeNaissance, String telephone, String matricule, LocalDate dateDeRecrutement, String email) {
        super(nom, prenom, dateDeNaissance, telephone);
        this.matricule = matricule;
        this.dateDeRecrutement = dateDeRecrutement;
        this.email = email;
    }

    public Employe(String matricule) {
        this.matricule = matricule;
    }
    public Employe() {

    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public LocalDate getDateDeRecrutement() {
        return dateDeRecrutement;
    }

    public void setDateDeRecrutement(LocalDate dateDeRecrutement) {
        this.dateDeRecrutement = dateDeRecrutement;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
