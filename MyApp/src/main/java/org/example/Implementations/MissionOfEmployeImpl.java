package org.example.Implementations;

import org.example.Entity.Employe;
import org.example.Entity.MissionOfEmploye;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.MissionOfEmployeInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class MissionOfEmployeImpl implements MissionOfEmployeInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<MissionOfEmploye> save(MissionOfEmploye missionOfEmploye) {
        try {
            String query = "INSERT INTO public.missionofemploye(mission, employe, datestart, dateend) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, missionOfEmploye.getMission().getCode());
            preparedStatement.setString(2, missionOfEmploye.getEmploye().getMatricule());
            preparedStatement.setString(3, LocalDateTime.now().toString());
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
    public Optional<List<MissionOfEmployeImpl>> findByEmploye(Employe employe) {
        try {
            String query = "";
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }
}
