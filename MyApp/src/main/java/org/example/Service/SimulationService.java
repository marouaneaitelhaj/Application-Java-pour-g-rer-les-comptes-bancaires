package org.example.Service;

import org.example.Entity.Credit;
import org.example.Interfaces.CreditInter;

import java.util.Optional;

public class SimulationService {
    private CreditInter creditInter;

    public SimulationService(CreditInter creditInter) {
        this.creditInter = creditInter;
    }

    public Optional<Credit> save(Credit credit) {
        return creditInter.save(credit);
    }

    public double createSimulation(int montant, int mensualite) {
        double a = montant * (Credit.TAUX / 12);
        System.out.println("a "+ a);
        double b = Math.pow(1 - (1 + (Credit.TAUX / mensualite)), -mensualite);
        System.out.println("b "+ b);
        return a/b;
    }
}
