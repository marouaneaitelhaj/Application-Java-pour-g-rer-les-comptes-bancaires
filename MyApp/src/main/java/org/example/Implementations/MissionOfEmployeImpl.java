package org.example.Implementations;

import org.example.Entity.Employe;
import org.example.Entity.Mission;
import org.example.Entity.MissionOfEmploye;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.MissionOfEmployeInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.RecursiveTask;

public class MissionOfEmployeImpl implements MissionOfEmployeInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<MissionOfEmploye> save(MissionOfEmploye missionOfEmploye) {
        try {
            String query = "INSERT INTO public.missionofemploye(mission, employe, datestart, dateend) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, missionOfEmploye.getMission().getCode());
            preparedStatement.setString(2, missionOfEmploye.getEmploye().getMatricule());
            preparedStatement.setString(3, LocalDate.now().toString());
            preparedStatement.setString(4, missionOfEmploye.getDateEnd().toString());
            preparedStatement.execute();
            return Optional.of(missionOfEmploye);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<MissionOfEmploye> update(MissionOfEmploye missionOfEmploye) {
        return Optional.empty();
    }

    @Override
    public int delete(MissionOfEmploye missionOfEmploye) {
        try {
            String query = "DELETE FROM public.missionofemploye WHERE mission=? AND employe=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(missionOfEmploye.getMission().getCode()));
            preparedStatement.setString(2, missionOfEmploye.getEmploye().getMatricule());
            if (preparedStatement.executeUpdate() == 0) {
                return 0;
            }
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public Optional<MissionOfEmploye> findOne(MissionOfEmploye missionOfEmploye) {
        return Optional.empty();
    }

    @Override
    public Optional<List<MissionOfEmploye>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<List<MissionOfEmploye>> findByEmploye(Employe employe) {
        try {
            List<MissionOfEmploye> missionOfEmployes = new ArrayList<MissionOfEmploye>();
            String query = "SELECT * FROM missionofemploye ms JOIN mission ON ms.mission = mission.code JOIN employe ON ms.employe = employe.matricule WHERE ms.employe =? ORDER BY ms.datestart DESC" ;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employe.getMatricule());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                MissionOfEmploye missionOfEmploye = new MissionOfEmploye();
                missionOfEmploye.setDateEnd(LocalDate.parse(resultSet.getString("dateend")));
                Mission mission = new Mission();
                mission.setCode(resultSet.getString("code"));
                mission.setNom(resultSet.getString("nomMission"));
                mission.setDescription(resultSet.getString("description"));
                missionOfEmploye.setMission(mission);
                employe.setNom(resultSet.getString("nom"));
                missionOfEmploye.setEmploye(employe);
                missionOfEmploye.setDateStart(LocalDate.parse(resultSet.getString("datestart")));
                System.out.println("test");
                missionOfEmployes.add(missionOfEmploye);
            }
            if (missionOfEmployes.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(missionOfEmployes);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }
}
