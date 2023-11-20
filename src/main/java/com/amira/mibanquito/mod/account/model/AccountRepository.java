/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.account.model;

import com.amira.mibanquito.mod.account.model.AccountEntity;
import com.amira.mibanquito.mod.bank.model.BankEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    AccountEntity findByibanCode(String ibanCode);

    public List<AccountEntity> findAllByEnabled(boolean enable);

    @Query("SELECT c FROM AccountEntity c WHERE c.enabled = enabled")
    public List<AccountEntity> enabledList(@Param(value = "id") boolean enabled);

}
