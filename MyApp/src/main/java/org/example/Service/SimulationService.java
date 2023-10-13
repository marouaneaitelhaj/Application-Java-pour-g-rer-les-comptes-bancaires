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

    public double createSimulation(Double capitale,int nombremensualite) {
        double tauxMensuel = (Credit.TAUX / 12) / 100;
        return (capitale * tauxMensuel * Math.pow(1 + tauxMensuel, nombremensualite))
                / (Math.pow(1 + tauxMensuel, nombremensualite) - 1);
    }
}
