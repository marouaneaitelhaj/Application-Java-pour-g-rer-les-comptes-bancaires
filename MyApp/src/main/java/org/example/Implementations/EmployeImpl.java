package org.example.Implementations;

import org.example.Entity.Employe;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.EmployeInter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeImpl implements EmployeInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<Employe> save(Employe employe) {
        try {
            String query = "INSERT INTO employe( nom, prenom, datedenaissance, telephone, matricule, datederecrutement, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employe.getNom());
            preparedStatement.setString(2, employe.getPrenom());
            preparedStatement.setDate(3, java.sql.Date.valueOf(employe.getDateDeNaissance()));
            preparedStatement.setString(4, employe.getTelephone());
            preparedStatement.setString(5, employe.getMatricule());
            preparedStatement.setDate(6, java.sql.Date.valueOf(employe.getDateDeRecrutement()));
            preparedStatement.setString(7, employe.getEmail());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows != 0) {
                return Optional.of(employe);
            }
        } catch (Exception e) {
            System.out.printf(String.valueOf(e));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employe> update(Employe employe) {
        try {
            String query = "UPDATE employe SET nom=?, prenom=?, telephone=? ,email=?, datederecrutement=?, datedenaissance=? WHERE matricule=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employe.getNom());
            preparedStatement.setString(2, employe.getPrenom());
            preparedStatement.setString(3, employe.getTelephone());
            preparedStatement.setString(4, employe.getEmail());
            preparedStatement.setDate(5, Date.valueOf(employe.getDateDeRecrutement().toString()));
            preparedStatement.setDate(6, Date.valueOf(employe.getDateDeNaissance().toString()));
            preparedStatement.setString(7, employe.getMatricule());
            if (preparedStatement.executeUpdate() != 0) {
                return Optional.of(employe);
            }
        } catch (Exception e) {
            System.out.printf(String.valueOf(e));
        }
        return Optional.empty();
    }

    @Override
    public int delete(Employe employe) {

        try {
            String query = "DELETE FROM employe WHERE matricule=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employe.getMatricule());
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
    public Optional<Employe> findOne(Employe employe) {
        try {
            String query = "SELECT nom, prenom, telephone, matricule, email, datederecrutement, datedenaissance FROM employe WHERE matricule=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employe.getMatricule());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employe.setMatricule(resultSet.getString("matricule"));
                employe.setEmail(resultSet.getString("email"));
                employe.setDateDeNaissance(resultSet.getDate("datedenaissance").toLocalDate());
                employe.setDateDeRecrutement(resultSet.getDate("datederecrutement").toLocalDate());
                employe.setTelephone(resultSet.getString("telephone"));
                employe.setNom(resultSet.getString("nom"));
            }
            return Optional.of(employe);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Employe> findByAtr(String text) {
            List<Employe> employeArrayList = new ArrayList<Employe>();
        try {
            String query = "SELECT nom, prenom, telephone, matricule, email, datederecrutement, datedenaissance FROM employe WHERE nom LIKE ? OR  prenom LIKE ? OR  telephone LIKE ? OR  matricule LIKE ? OR  email LIKE ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + text + "%");
            preparedStatement.setString(2, "%" + text + "%");
            preparedStatement.setString(3, "%" + text + "%");
            preparedStatement.setString(4, "%" + text + "%");
            preparedStatement.setString(5, "%" + text + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employe employe = new Employe();
                employe.setNom(resultSet.getString("nom"));
                employe.setPrenom(resultSet.getString("prenom"));
                employe.setTelephone(resultSet.getString("telephone"));
                employe.setMatricule(resultSet.getString("matricule"));
                employe.setEmail(resultSet.getString("email"));
                employe.setDateDeRecrutement(resultSet.getDate("datederecrutement").toLocalDate());
                employe.setDateDeNaissance(resultSet.getDate("datedenaissance").toLocalDate());
                employeArrayList.add(employe);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return employeArrayList;
    }

    @Override
    public List<Employe> findAll() {
            List<Employe> employeArrayList = new ArrayList<Employe>();
        try {
            String query = "SELECT nom, prenom, telephone, matricule, email, datederecrutement, datedenaissance FROM employe;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Employe employe = new Employe();
                employe.setNom(resultSet.getString("nom"));
                employe.setPrenom(resultSet.getString("prenom"));
                employe.setTelephone(resultSet.getString("telephone"));
                employe.setMatricule(resultSet.getString("matricule"));
                employe.setEmail(resultSet.getString("email"));
                employe.setDateDeRecrutement(resultSet.getDate("datederecrutement").toLocalDate());
                employe.setDateDeNaissance(resultSet.getDate("datedenaissance").toLocalDate());
                employeArrayList.add(employe);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return employeArrayList;
    }
}
