package org.example.Implementations;

import org.example.Entity.Virement;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.VirementInter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class VirementImpl implements VirementInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<Virement> save(Virement virement) {
        try {
            String query = "BEGIN;" +
                    "UPDATE compte SET solde=? WHERE numero=?;" +
                    "UPDATE compte SET solde=? WHERE numero=?;" +
                    "INSERT INTO virment(comptedestinataire, compteemetteur, mantant) VALUES (?, ?, ?);" +
                    "COMMIT;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, virement.getCompteDestinataire().getSolde());
            preparedStatement.setString(2, virement.getCompteDestinataire().getNumero());
            preparedStatement.setInt(3, virement.getCompteEmetteur().getSolde());
            preparedStatement.setString(4, virement.getCompteEmetteur().getNumero());
            preparedStatement.setString(5, virement.getCompteDestinataire().getNumero());
            preparedStatement.setString(6, virement.getCompteEmetteur().getNumero());
            preparedStatement.setInt(7, virement.getMantant());
            preparedStatement.execute();
            return Optional.of(virement);
        } catch (Exception e) {

        }
        return Optional.empty();
    }

    @Override
    public Optional<Virement> update(Virement virement) {
        return Optional.empty();
    }

    @Override
    public int delete(Virement virement) {
        try {
            String query = "DELETE FROM public.virment WHERE comptedestinataire=? and compteemetteur=? and mantant=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, virement.getCompteDestinataire().getNumero());
            preparedStatement.setString(2, virement.getCompteEmetteur().getNumero());
            preparedStatement.setInt(3, virement.getMantant());
            if (preparedStatement.executeUpdate() == 0) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    @Override
    public Optional<Virement> findOne(Virement virement) {
        return Optional.empty();
    }

    @Override
    public List<Virement> findAll() {
        return null;
    }
}
