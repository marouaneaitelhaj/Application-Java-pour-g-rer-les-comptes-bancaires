package org.example.Implementations;

import org.example.Entity.Employe;
import org.example.Entity.Mission;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.MissionInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MissionImpl implements MissionInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<Mission> save(Mission mission) {
        try {
            String query = "INSERT INTO mission(code, nomMission, description) VALUES (?,?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, mission.getCode());
            preparedStatement.setString(2, mission.getNom());
            preparedStatement.setString(3, mission.getDescription());
            preparedStatement.execute();
            return Optional.of(mission);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Mission> update(Mission mission) {
        return Optional.empty();
    }

    @Override
    public int delete(Mission mission) {
        try {
            String query = "DELETE FROM mission WHERE code=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, mission.getCode());
            if (preparedStatement.executeUpdate() == 0) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public Optional<Mission> findOne(Mission mission) {
        return Optional.empty();
    }

    @Override
    public List<Mission> findAll() {
        List<Mission> missions = new ArrayList<Mission>();
        try {
            String query = "SELECT code, nomMission, description FROM mission;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Mission mission = new Mission();
                mission.setCode(resultSet.getString("code"));
                mission.setNom(resultSet.getString("nomMission"));
                mission.setDescription(resultSet.getString("description"));
                missions.add(mission);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return missions;
    }
}
