/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.movement.model;

import com.amira.mibanquito.mod.movement.model.MovementEntity;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.amira.mibanquito.mod.movement.model.MovementRepository;

@Service
public class MovementService {

    @Autowired

    private MovementRepository repository;

    public MovementRepository getRepository() {
        return repository;
    }

    public MovementEntity create(MovementEntity arg) {
        var movement = new MovementEntity();
        movement.setId(UUID.randomUUID().toString());
        movement.setAmount(arg.getAmount());
        movement.setAccount(arg.getAccount());
        movement.setDeposit(arg.getDeposit());
        movement.setTransfer(arg.getTransfer());
        return repository.save(movement);
    }

    public MovementEntity update(String id, MovementEntity arg) {
        var movement = repository.findById(id).get();
        movement.setAmount(arg.getAmount());
        movement.setAccount(arg.getAccount());
        movement.setDeposit(arg.getDeposit());
        movement.setTransfer(arg.getTransfer());
        return repository.save(movement);
    }

    public void delete(String id) {
        var movement = repository.findById(id).get();
        repository.delete(movement);
    }

    public void enable(String id) {
        var movement = repository.findById(id).get();
        movement.setEnabled(true);
        repository.save(movement);
    }

    public void disable(String id) {
        var movement = repository.findById(id).get();
        movement.setEnabled(false);
        repository.save(movement);
    }
}
