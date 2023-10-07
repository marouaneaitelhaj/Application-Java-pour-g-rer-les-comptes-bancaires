package org.example.Implementations;

import org.example.Entity.Agence;
import org.example.Entity.AgenceOfEmploye;
import org.example.Entity.Employe;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.AgenceInter;
import org.example.Interfaces.AgenceOfEmployeInter;
import org.example.Interfaces.EmployeInter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgenceOfEmployeImpl implements AgenceOfEmployeInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();
    private AgenceInter agenceInter;

    public AgenceOfEmployeImpl(AgenceInter agenceInter, EmployeInter employeInter) {
        this.agenceInter = agenceInter;
        this.employeInter = employeInter;
    }

    private EmployeInter employeInter;


    @Override
    public Optional<AgenceOfEmploye> save(AgenceOfEmploye agenceOfEmploye) {
        try {
            String query = "INSERT INTO public.employeagencelogs(date, employe, agence, status) VALUES (?, ?, ?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(2, agenceOfEmploye.getEmploye().getMatricule());
            preparedStatement.setString(3, agenceOfEmploye.getAgence().getCode());
            preparedStatement.setString(4, agenceOfEmploye.getAffectationStatus().name());
            int rowaffected = preparedStatement.executeUpdate();
            if (rowaffected == 0) {
                return Optional.empty();
            } else {
                return Optional.of(agenceOfEmploye);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<AgenceOfEmploye> update(AgenceOfEmploye agenceOfEmploye) {
        try {
            String query = "UPDATE employeagencelogs SET status=? WHERE date=? and employe=? and agence=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, agenceOfEmploye.getAffectationStatus().name());
            preparedStatement.setDate(2, Date.valueOf(agenceOfEmploye.getDate()));
            preparedStatement.setString(3, agenceOfEmploye.getEmploye().getMatricule());
            preparedStatement.setString(4, agenceOfEmploye.getAgence().getCode());
            if (preparedStatement.executeUpdate() == 0) {
                return Optional.empty();
            } else {
                return Optional.of(agenceOfEmploye);
            }
        } catch (Exception e) {

        }

        return Optional.empty();
    }

    @Override
    public int delete(AgenceOfEmploye agenceOfEmploye) {
        return 0;
    }

    @Override
    public Optional<AgenceOfEmploye> findOne(AgenceOfEmploye agenceOfEmploye) {
        return Optional.empty();
    }

    @Override
    public List<AgenceOfEmploye> findAll() {
        List<AgenceOfEmploye> agenceOfEmployes = new ArrayList<>();
        try {
            String query = "SELECT * FROM employeagencelogs;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                AgenceOfEmploye agenceOfEmploye = new AgenceOfEmploye();
                Employe employe = new Employe(resultSet.getString("employe"));
                Agence agence = new Agence(resultSet.getString("agence"));
                agenceOfEmploye.setDate(resultSet.getDate("date").toLocalDate());
                Optional<Employe> optionalEmploye = this.employeInter.findOne(employe);
                if (optionalEmploye.isPresent()) {
                    employe = optionalEmploye.get();
                }
                Optional<Agence> agenceOptional = this.agenceInter.findOne(agence);
                if (agenceOptional.isPresent()){
                    agence = agenceOptional.get();
                }
                agenceOfEmploye.setEmploye(employe);
                agenceOfEmploye.setAgence(agence);
                agenceOfEmployes.add(agenceOfEmploye);
            }
        } catch (Exception e) {

        }
        return agenceOfEmployes;
    }
}
