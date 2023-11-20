/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.bank.endpoint;

import com.amira.mibanquito.mod.bank.model.BankEntity;
import com.amira.mibanquito.mod.bank.model.BankService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankEndpoint {

    @Autowired
    private BankService service;

    @PostMapping
    public BankEntity create(@RequestBody BankEntity arg) {

        return service.create(arg);
    }

    @GetMapping
    public void list() {

    }

    @GetMapping("/{id}")
    public BankEntity find(@PathVariable("id") String id) {
        return service.getRepository().findById(id).get();
    }

   @PutMapping("/{id}")
  public BankEntity update(@PathVariable("id") String id, @RequestBody BankEntity arg) {
    return service.update(id, arg);

  }

  @PutMapping("/{id}/enable")
  public void enable(@PathVariable("id") String id) {
    service.enable(id);
  }

  @PutMapping("/{id}/disable")
  public void disable(@PathVariable("id") String id) {
    service.disable(id);
  }
}

