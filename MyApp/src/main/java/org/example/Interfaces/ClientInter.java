package org.example.Interfaces;

import org.example.Entity.Client;
import org.example.Repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientInter extends CrudRepository<Client> {
    List<Client> findByAtr(String text);
}
