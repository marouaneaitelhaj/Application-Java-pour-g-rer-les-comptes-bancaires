package org.example.Implementations;

import org.example.Entity.Compte;
import org.example.Entity.Employe;
import org.example.Entity.Operation;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.OperationInter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class OperationImpl implements OperationInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<Operation> save(Operation operation) {
        try {
            String query = "BEGIN;" +
                    "INSERT INTO operation(datedecreation, montant, employe, compte) VALUES (?, ?, ?, ?);" +
                    "UPDATE compte SET solde = ? WHERE numero=?;" +
                    "COMMIT;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(operation.getDateDeCreation()));
            preparedStatement.setDouble(2, operation.getMontant());
            preparedStatement.setString(3, operation.getEmploye().getMatricule());
            preparedStatement.setString(4, operation.getCompte().getNumero());
            preparedStatement.setInt(5, operation.getCompte().getSolde());
            preparedStatement.setString(6, operation.getCompte().getNumero());
            preparedStatement.execute();
            return Optional.of(operation);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Operation> update(Operation operation) {
        return Optional.empty();
    }

    @Override
    public int delete(Operation operation) {
        try {
            String query = "DELETE FROM operation WHERE numero=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, operation.getNumero());
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
    public Optional<Operation> findOne(Operation operation) {
        try {
            String query = "SELECT montant, employe, compte, numero, datedecreation FROM operation WHERE numero=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, operation.getNumero());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                operation.setDateDeCreation(LocalDate.parse(resultSet.getString("datedecreation")));
                Compte compte = new Compte();
                compte.setNumero(resultSet.getString("compte"));
                operation.setCompte(compte);
                operation.setMontant(Integer.parseInt(resultSet.getString("montant")));
                Employe employe = new Employe();
                employe.setMatricule(resultSet.getString("employe"));
                operation.setEmploye(employe);
                operation.setNumero(Integer.parseInt(resultSet.getString("numero")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.of(operation);
    }

    @Override
    public Optional<List<Operation>> findAll() {
        return null;
    }
}
