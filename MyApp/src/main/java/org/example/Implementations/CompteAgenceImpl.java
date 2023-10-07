package org.example.Implementations;

import org.example.Entity.Agence;
import org.example.Entity.Compte;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.CompteAgenceIntre;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CompteAgenceImpl implements CompteAgenceIntre {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public boolean affectCompteAgence(Compte compte, Agence agence) {
        try {
            String query = "UPDATE compte SET agence=? WHERE numero=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, agence.getCode());
            preparedStatement.setString(2, compte.getNumero());
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }
}
