/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.movement.endpoint;

import com.amira.mibanquito.mod.movement.model.MovementEntity;
import com.amira.mibanquito.mod.movement.model.MovementService;
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
@RequestMapping("/movement")
public class MovementEndpoint {

    @Autowired
    private MovementService service;

    @PostMapping
    public MovementEntity create(@RequestBody MovementEntity arg) {

        return service.create(arg);
    }

    @GetMapping
    public void list() {

    }

    @GetMapping("/{id}")
    public MovementEntity find(@PathVariable("id") String id) {
        return service.getRepository().findById(id).get();
    }

    @PutMapping("/{id}")
  public  MovementEntity update(@PathVariable("id") String id, @RequestBody  MovementEntity arg) {
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
