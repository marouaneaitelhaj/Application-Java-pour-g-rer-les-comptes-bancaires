package org.example.Implementations;

import org.example.Entity.Operation;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.OperationInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class OperationImpl implements OperationInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<Operation> save(Operation operation) {
        try {
            String query = "INSERT INTO public.operation(numero, datedecreation, montant, employe, compte) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, operation.getNumero());
            preparedStatement.setString(2, operation.getDateDeCreation());
            preparedStatement.setString(3, operation.getMontant());
            preparedStatement.setString(4, operation.getEmploye().getMatricule());
            preparedStatement.setString(5, operation.getCompte().getNumero());
            preparedStatement.execute();
            return Optional.of(operation);
        } catch (Exception e) {
        }
        return Optional.empty();
    }

    @Override
    public Optional<Operation> update(Operation operation) {
        return Optional.empty();
    }

    @Override
    public int delete(Operation operation) {
        return 0;
    }

    @Override
    public Optional<Operation> findOne(Operation operation) {
        return Optional.empty();
    }

    @Override
    public List<Operation> findAll() {
        return null;
    }
}
