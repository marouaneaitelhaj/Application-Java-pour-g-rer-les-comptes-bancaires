package org.example.Implementations;

import org.example.Entity.AgenceOfEmploye;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.AgenceOfEmployeInter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AgenceOfEmployeImpl implements AgenceOfEmployeInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<AgenceOfEmploye> save(AgenceOfEmploye agenceOfEmploye) {
        try {
            String query = "INSERT INTO public.employeagencelogs(date, employe, agence) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(2, agenceOfEmploye.getEmploye().getMatricule());
            preparedStatement.setString(3, agenceOfEmploye.getAgence().getCode());
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
    public Optional<List<AgenceOfEmploye>> findAll() {
        return Optional.empty();
    }
}
