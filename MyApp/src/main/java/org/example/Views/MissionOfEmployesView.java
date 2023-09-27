package org.example.Views;

import org.example.Entity.Employe;
import org.example.Entity.Mission;
import org.example.Entity.MissionOfEmploye;
import org.example.Helpers.MyFunction;
import org.example.Implementations.MissionOfEmployeImpl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MissionOfEmployesView {
    MissionOfEmployeImpl missionOfEmployeImpl = new MissionOfEmployeImpl();
    Scanner scanner = new Scanner(System.in);

    public MissionOfEmployesView() {
        System.out.println("1- Créer une nouvelle affectation");
        System.out.println("2- Supprimer une affectation");
        System.out.println("3- Afficher l'historique des affectations d'un employé");
        System.out.println("4- Statistiques sur les affectation");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.saveView();
            }
            case "2" -> {
                this.deleteView();
            }
            case "3" -> {
                this.findByEmployeView();
            }
            case "4" -> {
                System.out.println("1- Employe Statistiques");
                System.out.println("2- Mission Statistiques");
                switch (scanner.nextLine()) {
                    case "1" -> {
                        this.EmployeStatistiques();
                    }
                    case "2" -> {
                        this.MissionStatistiques();
                    }
                    default -> {
                        System.out.println("Vous devez choisir un choix valide");
                        new MissionOfEmployesView();
                    }
                }
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new MissionView();
            }
        }
    }

    private void MissionStatistiques() {
        Optional<HashMap<String, Integer>> missionStatistiques = missionOfEmployeImpl.MissionStatistiques();
        if (missionStatistiques.isEmpty()) {
            System.out.println("rien à montrer");
        } else {
            missionStatistiques.get().forEach((s, integer) -> {
                System.out.println(s + "     " + integer);
            });
        }
    }

    private void EmployeStatistiques() {
        Optional<HashMap<String, Integer>> employeStatistiques = missionOfEmployeImpl.EmployeStatistiques();
        if (employeStatistiques.isEmpty()) {
            System.out.println("rien à montrer");
        } else {
            employeStatistiques.get().forEach((s, integer) -> {
                System.out.println(s + "     " + integer);
            });
        }
    }

    private void findByEmployeView() {
        System.out.println("Employe:");
        MissionOfEmploye missionOfEmploye = new MissionOfEmploye();
        Employe employe = new Employe();
        employe.setMatricule(scanner.nextLine());
        missionOfEmploye.setEmploye(employe);
        Optional<List<MissionOfEmploye>> optionalmissionOfEmployes = missionOfEmployeImpl.findByEmploye(employe);
        List<MissionOfEmploye> missionOfEmployes = optionalmissionOfEmployes.get();
        missionOfEmployes.forEach(missionOfEmploye1 -> {
            System.out.println(missionOfEmploye1.getEmploye().getNom() + "       " + missionOfEmploye1.getMission().getNom() + "       " + missionOfEmploye1.getDateStart() + "       ");
        });

    }

    private void deleteView() {
        MissionOfEmploye missionOfEmploye = new MissionOfEmploye();
        System.out.println("mission:");
        Mission mission = new Mission();
        mission.setCode(scanner.nextLine());
        missionOfEmploye.setMission(mission);
        System.out.println("employe:");
        Employe employe = new Employe();
        employe.setMatricule(scanner.nextLine());
        missionOfEmploye.setEmploye(employe);
        if (missionOfEmployeImpl.delete(missionOfEmploye) == 0) {
            System.out.println("l'affectation n'a pas été supprimée");
        } else {
            System.out.println("affectation supprimée");
        }
        new MissionView();
    }

    public void saveView() {
        MissionOfEmploye missionOfEmploye = new MissionOfEmploye();
        System.out.println("mission:");
        Mission mission = new Mission();
        mission.setCode(scanner.nextLine());
        missionOfEmploye.setMission(mission);
        System.out.println("employe:");
        Employe employe = new Employe();
        employe.setMatricule(scanner.nextLine());
        missionOfEmploye.setEmploye(employe);
        LocalDate endDate = MyFunction.getDate("End date : (yyyy-mm-dd)");
        missionOfEmploye.setDateEnd(endDate);
        if (missionOfEmployeImpl.save(missionOfEmploye).isEmpty()) {
            System.out.println("l'affectation n'a pas été ajoutée");
        } else {
            System.out.println("affectation ajoutée");
        }
        new MissionView();
    }
}
