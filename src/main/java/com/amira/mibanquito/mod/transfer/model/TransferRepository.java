/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.transfer.model;
import com.amira.mibanquito.mod.transfer.model.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, String>{
    

    public TransferEntity save(TransferEntity transfer);
  
}

