/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.account.model;

import com.amira.mibanquito.mod.bank.model.BankEntity;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public AccountRepository getRepository() {
        return repository;
    }

    public AccountEntity create(AccountEntity arg) {
        var account = new AccountEntity();
        account.setDocument(arg.getDocument());
        account.setIbanCode(arg.getIbanCode());
        account.setId(UUID.randomUUID().toString());
        account.setCoin(arg.getCoin());
        account.setBank(arg.getBank());
        account.setHolder(arg.getHolder());
        return repository.save(account);
    }

    public AccountEntity update(String id, AccountEntity arg) {
        var account = repository.findById(id).get();
        account.setDocument(arg.getDocument());
        account.setIbanCode(arg.getIbanCode());
        account.setCoin(arg.getCoin());
        account.setBank(arg.getBank());
        account.setHolder(arg.getHolder());
        return repository.save(account);
    }

    public void delete(String id) {
        var account = repository.findById(id).get();
        repository.delete(account);
    }

    public void enable(String id) {
        var account = repository.findById(id).get();
        account.setEnabled(true);
        repository.save(account);
    }

    public void disable(String id) {
        var account = repository.findById(id).get();
        account.setEnabled(false);
        repository.save(account);
    }

    public AccountEntity findByIbanCode(String iban) {
        if (iban == null) {
            return null;
        }
        return repository.findByibanCode(iban);

    }
}
