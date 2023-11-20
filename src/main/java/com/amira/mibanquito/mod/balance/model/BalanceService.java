/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.balance.model;

import com.amira.mibanquito.mod.account.model.AccountEntity;
import com.amira.mibanquito.mod.balance.model.BalanceEntity;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.amira.mibanquito.mod.balance.model.BalanceRepository;
import com.amira.mibanquito.mod.bank.model.BankEntity;
import jakarta.persistence.ManyToOne;

@Service
public class BalanceService {

    @ManyToOne
    protected AccountEntity account;
    @Autowired
    private BalanceRepository repository;

    public BalanceRepository getRepository() {
        return repository;
    }

    public BalanceEntity create(BalanceEntity arg) {
        var balance = new BalanceEntity();
        balance.setIsoCode(arg.getIsoCode());
        balance.setAccount(arg.getAccount());
        balance.setId(UUID.randomUUID().toString());
        balance.setAmount(arg.getAmount());
        return repository.save(balance);
    }

    public BalanceEntity update(String id, BalanceEntity arg) {
        var balance = repository.findById(id).get();
        balance.setIsoCode(arg.getIsoCode());
        balance.setAccount(arg.getAccount());
        balance.setAmount(arg.getAmount());
        return repository.save(balance);
    }

    public void delete(String id) {
        var balance = repository.findById(id).get();
        repository.delete(balance);
    }

    public void enable(String id) {
        var balance = repository.findById(id).get();
        balance.setEnabled(true);
        repository.save(balance);
    }

    public void disable(String id) {
        var balance = repository.findById(id).get();
        balance.setEnabled(false);
        repository.save(balance);
    }

    public BalanceEntity findByAccount(AccountEntity account) {
        if (account == null) {
            return null;
        }
        return repository.findByAccount(account);

    }

}
