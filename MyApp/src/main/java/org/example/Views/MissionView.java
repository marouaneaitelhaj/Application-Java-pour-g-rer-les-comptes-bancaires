package org.example.Views;

import org.example.Entity.Employe;
import org.example.Entity.Mission;
import org.example.Entity.MissionOfEmploye;
import org.example.Helpers.MyFunction;
import org.example.Implementations.MissionImpl;
import org.example.Implementations.MissionOfEmployeImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MissionView {
    MissionImpl missionImpl = new MissionImpl();
    Scanner scanner = new Scanner(System.in);

    public MissionView() {
        System.out.println("1- Ajouter une mission");
        System.out.println("2- Supprimer une mission");
        System.out.println("3- Afficher le liste des missions");
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
