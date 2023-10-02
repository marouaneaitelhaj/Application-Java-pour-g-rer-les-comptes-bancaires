package org.example.Entity;

import java.time.LocalDate;

public class Client extends Person {
    private String code;

    private String adresse;

    public Client(String nom, String prenom, LocalDate dateDeNaissance, String telephone, String code, String adresse) {
        super(nom, prenom, dateDeNaissance, telephone);
        setCode(code);
        setCode(adresse);
    }
    public Client(){

    }
    public Client(String code){
        setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
