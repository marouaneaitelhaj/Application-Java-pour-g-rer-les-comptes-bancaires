package org.example.Implementations;

import org.example.Entity.Agence;
import org.example.Entity.Client;
import org.example.Entity.Credit;
import org.example.Enums.CreditEtat;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.CreditInter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreditImpl implements CreditInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<Credit> save(Credit credit) {
        try {
            String query = "INSERT INTO credit(client, agence, date, montant, duree, remarques,etat) VALUES (?, ?, ?, ?, ?, ?,?) RETURNING *;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, credit.getClient().getCode());
            preparedStatement.setString(2, credit.getAgence().getCode());
            preparedStatement.setString(3, Date.valueOf(LocalDate.now()).toString());
            preparedStatement.setDouble(4, credit.getMontant());
            preparedStatement.setDouble(5, credit.getDuree());
            preparedStatement.setString(6, credit.getRemarques());
            preparedStatement.setString(7, credit.getCreditEtat().name());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                credit.setNumero(resultSet.getString("numero"));
                credit.setClient(new Client(resultSet.getString("client")));
                credit.setAgence(new Agence(resultSet.getString("agence")));
                credit.setDate(LocalDate.parse(resultSet.getString("date")));
                credit.setMontant(resultSet.getDouble("montant"));
                credit.setDuree(resultSet.getDouble("duree"));
                credit.setRemarques(resultSet.getString("remarques"));
                credit.setCreditEtat(CreditEtat.valueOf(resultSet.getString("etat")));
                return Optional.of(credit);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Credit> update(Credit credit) {
        try {
            String query = "UPDATE credit SET etat=? WHERE numero=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, credit.getCreditEtat().name());
            preparedStatement.setString(2, credit.getNumero());
            if (preparedStatement.executeUpdate() != 0) {
                return Optional.of(credit);
            }
        } catch (Exception e) {

        }

        return Optional.empty();
    }

    @Override
    public int delete(Credit credit) {
        return 0;
    }

    @Override
    public Optional<Credit> findOne(Credit credit) {
        try {
            String query = "SELECT * FROM credit WHERE numero=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, credit.getNumero());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                credit.setNumero(resultSet.getString("numero"));
                credit.setClient(new Client(resultSet.getString("client")));
                credit.setAgence(new Agence(resultSet.getString("agence")));
                credit.setDate(LocalDate.parse(resultSet.getString("date")));
                credit.setMontant(resultSet.getDouble("montant"));
                credit.setDuree(resultSet.getDouble("duree"));
                credit.setRemarques(resultSet.getString("remarques"));
                credit.setCreditEtat(CreditEtat.valueOf(resultSet.getString("etat")));
            }
            return Optional.of(credit);
        } catch (Exception e) {

        }
        return Optional.empty();
    }

    @Override
    public List<Credit> findAll() {
        List<Credit> creditList = new ArrayList<>();
        try {
            String query = "SELECT * FROM credit;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Credit credit = new Credit();
                Client client = new Client(resultSet.getString("client"));
                Agence agence = new Agence(resultSet.getString("agence"));
                credit.setDate(LocalDate.parse(resultSet.getString("date")));
                credit.setMontant(resultSet.getDouble("montant"));
                credit.setDuree(resultSet.getDouble("duree"));
                credit.setRemarques(resultSet.getString("remarques"));
                credit.setCreditEtat(CreditEtat.valueOf(resultSet.getString("etat")));
                creditList.add(credit);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return creditList;
    }

    @Override
    public List<Credit> findAllBystatus() {
        List<Credit> creditList = new ArrayList<>();
        try {
            String query = "SELECT * FROM credit ORDER BY etat;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Credit credit = new Credit();
                Client client = new Client(resultSet.getString("client"));
                Agence agence = new Agence(resultSet.getString("agence"));
                credit.setDate(LocalDate.parse(resultSet.getString("date")));
                credit.setMontant(resultSet.getDouble("montant"));
                credit.setDuree(resultSet.getDouble("duree"));
                credit.setRemarques(resultSet.getString("remarques"));
                credit.setCreditEtat(CreditEtat.valueOf(resultSet.getString("etat")));
                creditList.add(credit);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return creditList;
    }

    @Override
    public List<Credit> findAllByDate() {
        List<Credit> creditList = new ArrayList<>();
        try {
            String query = "SELECT * FROM credit ORDER BY date;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Credit credit = new Credit();
                Client client = new Client(resultSet.getString("client"));
                Agence agence = new Agence(resultSet.getString("agence"));
                credit.setDate(LocalDate.parse(resultSet.getString("date")));
                credit.setMontant(resultSet.getDouble("montant"));
                credit.setDuree(resultSet.getDouble("duree"));
                credit.setRemarques(resultSet.getString("remarques"));
                credit.setCreditEtat(CreditEtat.valueOf(resultSet.getString("etat")));
                creditList.add(credit);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return creditList;
    }
}
