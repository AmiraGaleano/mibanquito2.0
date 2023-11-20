/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.deposit.model;

import com.amira.mibanquito.mod.deposit.model.DepositEntity;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.amira.mibanquito.mod.movement.model.MovementRepository;

@Service
public class DepositService {

    @Autowired
    private DepositRepository repository;

    public DepositRepository getRepository() {
        return repository;
    }

    public DepositEntity create(DepositEntity arg) {
        var deposit = new DepositEntity();
        deposit.setAmount(arg.getAmount());
        deposit.setDateTime(arg.getDateTime());
        deposit.setSuccessful(arg.getSuccessful());
        deposit.setConcept(arg.getConcept());
        deposit.setDoc(arg.getDoc());
        deposit.setSwiftCode(arg.getSwiftCode());
        deposit.setId(UUID.randomUUID().toString());
        deposit.setIbanCode(arg.getIbanCode());
        deposit.setIsoCode(arg.getIsoCode());
        return repository.save(deposit);
    }

    public DepositEntity update(String id, DepositEntity arg) {
        var deposit = repository.findById(id).get();
        deposit.setAmount(arg.getAmount());
        deposit.setDateTime(arg.getDateTime());
        deposit.setSuccessful(arg.getSuccessful());
        deposit.setConcept(arg.getConcept());
        deposit.setSwiftCode(arg.getSwiftCode());
        deposit.setDoc(arg.getDoc());
        deposit.setIbanCode(arg.getIbanCode());
        deposit.setIsoCode(arg.getIsoCode());
        return repository.save(deposit);
    }

    public void delete(String id) {
        var deposit = repository.findById(id).get();
        repository.delete(deposit);
    }

    public void enable(String id) {
        var deposit = repository.findById(id).get();
        deposit.setEnabled(true);
        repository.save(deposit);
    }

    public void disable(String id) {
        var deposit = repository.findById(id).get();
        deposit.setEnabled(false);
        repository.save(deposit);
    }

    public void saveDeposit(DepositEntity deposit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
