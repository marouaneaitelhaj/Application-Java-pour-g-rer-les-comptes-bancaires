package org.example.Entity;

public class Courant extends Compte {
    private Double decouvert;

    public Courant(Double decouvert) {
        this.decouvert = decouvert;
    }

    public Courant(Compte compte) {
        super(compte.getNumero(), compte.getSolde(), compte.getDate(), compte.getCompteEtat(), compte.getClient());
    }

    public Double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(Double decouvert) {
        this.decouvert = decouvert;
    }
}
