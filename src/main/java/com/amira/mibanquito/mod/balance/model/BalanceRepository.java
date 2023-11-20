/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.balance.model;

import com.amira.mibanquito.mod.account.model.AccountEntity;
import com.amira.mibanquito.mod.balance.model.BalanceEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface BalanceRepository extends JpaRepository<BalanceEntity, String> {

    public BalanceEntity findByAccount(AccountEntity account);

    public List<BalanceEntity> findAllByEnabled(boolean enable);

    @Query("SELECT c FROM BankEntity c WHERE c.enabled = enabled")
    public List<BalanceEntity> enabledList(@Param(value = "id") boolean enabled);

}
