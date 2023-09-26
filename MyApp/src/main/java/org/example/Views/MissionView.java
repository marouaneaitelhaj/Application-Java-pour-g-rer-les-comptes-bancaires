package org.example.Views;

import org.example.Entity.Employe;
import org.example.Entity.Mission;
import org.example.Entity.MissionOfEmploye;
import org.example.Helpers.MyFunction;
import org.example.Implementations.MissionImpl;
import org.example.Implementations.MissionOfEmployeImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MissionView {
    MissionImpl missionImpl = new MissionImpl();
    MissionOfEmployeImpl missionOfEmployeImpl = new MissionOfEmployeImpl();
    Scanner scanner = new Scanner(System.in);

    public MissionView() {
        System.out.println("1- Ajouter une mission");
        System.out.println("2- Supprimer une mission");
        System.out.println("3- Afficher le liste des missions");
        System.out.println("4- Créer une nouvelle affectation");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.saveView();
            }
            case "2" -> {
                this.deleteView();
            }
            case "3" -> {
                this.showView();
            }
            case "4" -> {
                this.nouvelleAffectation();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new MissionView();
            }
        }
    }

    private void deleteView() {
        System.out.println("code :");
        Mission mission = new Mission();
        mission.setCode(scanner.nextInt());
        if (missionImpl.delete(mission) == 0) {
            System.out.println("la mission n'a pas été supprimée");
        } else {
            System.out.println("mission supprimée");
        }
        new MissionView();
    }

    public void nouvelleAffectation() {
        MissionOfEmploye missionOfEmploye = new MissionOfEmploye();
        System.out.println("mission:");
        Mission mission = new Mission();
        mission.setCode(Integer.parseInt(scanner.nextLine()));
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
    }

    private void showView() {
        Optional<List<Mission>> optionalMission = missionImpl.findAll();

        if (optionalMission.isPresent()) {
            List<Mission> missions = optionalMission.get();
            if (missions.isEmpty()) {
                System.out.println("aucune mission trouvée");
                new MissionView();
            } else {
                missions.forEach(mission -> {
                    System.out.println(mission.getCode() + "     " + mission.getNom() + "     " + mission.getDescription());
                });
            }
        }
    }

    private void saveView() {
        Mission mission = new Mission();
        System.out.println("nom: ");
        mission.setNom(scanner.nextLine());
        System.out.println("description: ");
        mission.setDescription(scanner.nextLine());
        if (missionImpl.save(mission).isEmpty()) {
            System.out.println("la mission n'a pas été ajoutée");
        } else {
            System.out.println("mission ajoutée");
        }
        new MissionView();
    }
}
