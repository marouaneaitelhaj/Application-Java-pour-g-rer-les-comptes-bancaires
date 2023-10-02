package org.example.Entity;

import java.sql.Date;
import java.time.LocalDate;

public class Employe extends Person {
    private String matricule;
    private LocalDate dateDeRecrutement;
    private String email;


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
