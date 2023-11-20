/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.transfer.model;

import com.amira.mibanquito.mod.transfer.model.TransferEntity;
import com.amira.mibanquito.mod.transfer.model.TransferRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TransferService {

    @Autowired
    private TransferRepository repository;

    public TransferRepository getRepository() {
        return repository;
    }

    public TransferEntity create(TransferEntity arg) {
        var transfer = new TransferEntity();
        transfer.setSuccessful(arg.getSuccessful());
        transfer.setDateTime(arg.getDateTime());
        transfer.setReceiverIban(arg.getReceiverIban());
        transfer.setReceiverSwift(arg.getReceiverSwift());
        transfer.setReceiverDoc(arg.getReceivertDoc());
        transfer.setId(UUID.randomUUID().toString());
        transfer.setConcept(arg.getConcept());
        transfer.setSenderIban(arg.getSenderIban());
        transfer.setSenderSwift(arg.getSenderSwift());
        transfer.setSenderDoc(arg.getSenderDoc());
        transfer.setAmount(arg.getAmount());
        transfer.setCoinIsoCode(arg.getCoinIsoCode());
        return repository.save(transfer);
    }

    public TransferEntity update(String id, TransferEntity arg) {
        var transfer = repository.findById(id).get();
        transfer.setSuccessful(arg.getSuccessful());
        transfer.setDateTime(arg.getDateTime());
        transfer.setReceiverIban(arg.getReceiverIban());
        transfer.setReceiverSwift(arg.getReceiverSwift());
        transfer.setReceiverDoc(arg.getReceivertDoc());
        transfer.setConcept(arg.getConcept());
        transfer.setSenderIban(arg.getSenderIban());
        transfer.setSenderSwift(arg.getSenderSwift());
        transfer.setSenderDoc(arg.getSenderDoc());
        transfer.setAmount(arg.getAmount());
        transfer.setCoinIsoCode(arg.getCoinIsoCode());
        return repository.save(transfer);
    }

    public void delete(String id) {
        var transfer = repository.findById(id).get();
        repository.delete(transfer);
    }

    public void enable(String id) {
        var transfer = repository.findById(id).get();
        transfer.setEnabled(true);
        repository.save(transfer);
    }

    public void disable(String id) {
        var transfer = repository.findById(id).get();
        transfer.setEnabled(false);
        repository.save(transfer);
    }
}
