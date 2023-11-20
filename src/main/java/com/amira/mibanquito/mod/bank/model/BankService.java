package com.amira.mibanquito.mod.bank.model;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BankService {

 
    @Autowired
    private BankRepository repository;

    public BankRepository getRepository() {
        return repository;
    }

    public BankEntity create(BankEntity arg) {
        var bank = new BankEntity();
        bank.setId(UUID.randomUUID().toString());
        bank.setName(arg.getName());
        bank.setSwift(arg.getSwift());
        return repository.save(bank);
    }
    public BankEntity update(String id, BankEntity arg) {
    var bank = repository.findById(id).get();
    bank.setName(arg.getName());
     bank.setSwift(arg.getSwift());
    return repository.save(bank);
  }

  public void delete(String id) {
    var bank = repository.findById(id).get();
    repository.delete(bank);
  }

  public void enable(String id) {
    var bank = repository.findById(id).get();
    bank.setEnabled(true);
    repository.save(bank);
  }

  public void disable(String id) {
    var bank = repository.findById(id).get();
    bank.setEnabled(false);
    repository.save(bank);
  }
  public BankEntity findBySwiftCode(String swift){
       if (swift==null){
           return null;
       }
       return repository.findBySwiftCode(swift);
       
    }

    
}
