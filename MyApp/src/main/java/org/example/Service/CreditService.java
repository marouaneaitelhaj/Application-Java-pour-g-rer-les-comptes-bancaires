package org.example.Service;

import org.example.Entity.Credit;
import org.example.Interfaces.CreditInter;

import java.util.List;
import java.util.Optional;

public class CreditService{
    private CreditInter creditInter;
    public CreditService(CreditInter creditInter) {
        this.creditInter = creditInter;
    }


    public List<Credit> findAllBystatus() {
        return creditInter.findAllBystatus();
    }


    public List<Credit> findAllByDate() {
        return creditInter.findAllByDate();
    }





    public Optional<Credit> update(Credit credit) {
        return creditInter.update(credit);
    }





    public Optional<Credit> findOne(Credit credit) {
        return creditInter.findOne(credit);
    }


    public List<Credit> findAll() {
        return creditInter.findAll();
    }
}
