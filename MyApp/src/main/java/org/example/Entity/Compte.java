package org.example.Entity;

import org.example.Enums.CompteEtat;

import java.time.LocalDate;

public class Compte {
    private int numero;
    private int solde;
    private LocalDate date;
    private CompteEtat compteEtat;
    private Client client;
}
