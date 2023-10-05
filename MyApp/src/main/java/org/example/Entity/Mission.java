package org.example.Entity;

import java.util.List;

public class Mission {
    private String code;
    private String nom;
    private String description;

    private List<MissionOfEmploye> missionOfEmployeList;

    public Mission(String code, String nom, String description) {
        this.code = code;
        this.nom = nom;
        this.description = description;
    }
    public Mission() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
