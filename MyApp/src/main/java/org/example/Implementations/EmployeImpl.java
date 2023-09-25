package org.example.Implementations;

import org.example.Entity.Employe;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.EmployeInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class EmployeImpl implements EmployeInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<Employe> save(Employe employe) {
        try {
            String query = "INSERT INTO public.employe( nom, prenom, datedenaissance, telephone, matricule, datederecrutement, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employe.getNom());
            preparedStatement.setString(2, employe.getPrenom());
            preparedStatement.setString(3, employe.getDateDeNaissance().toString());
            preparedStatement.setString(4, employe.getTelephone());
            preparedStatement.setString(5, employe.getMatricule());
            preparedStatement.setString(6, employe.getDateDeRecrutement().toString());
            preparedStatement.setString(7, employe.getEmail());
            preparedStatement.execute();
            return Optional.of(employe);
        } catch (Exception e) {
            System.out.printf(String.valueOf(e));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employe> update(Employe employe) {
        return null;
    }

    @Override
    public int delete(Employe employe) {

        try {
            String query = "DELETE FROM public.employe WHERE matricule=?";
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
            String query = "SELECT nom, prenom, telephone, matricule, email, datederecrutement, datedenaissance FROM public.employe WHERE matricule=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employe.getMatricule());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employe.setMatricule(resultSet.getString("matricule"));
                employe.setEmail(resultSet.getString("email"));
                employe.setDateDeNaissance(LocalDate.parse(resultSet.getString("datedenaissance")));
                employe.setDateDeRecrutement(LocalDate.parse(resultSet.getString("datederecrutement")));
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
    public List<Employe> findAll() {
        return null;
    }
}
