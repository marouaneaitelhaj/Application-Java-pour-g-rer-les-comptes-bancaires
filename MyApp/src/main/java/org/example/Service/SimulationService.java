package org.example.Service;

public class SimulationService {

    public int createSimulation(int capitalEmprunte, int tauxAnnuelProportionnel, int nombreDeMensualites) {
        double a = capitalEmprunte * (tauxAnnuelProportionnel / 12);
        double b = Math.pow(1 - (1 + (tauxAnnuelProportionnel / 12)), -nombreDeMensualites);
        return (int) (a / b);
    }
}
