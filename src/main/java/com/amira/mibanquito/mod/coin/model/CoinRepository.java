/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.coin.model;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface CoinRepository extends JpaRepository<CoinEntity, String>{

    public CoinEntity  findByIsoCode(String isoCode);
    
    public List<CoinEntity> findAllByEnabled(boolean enable);
  

    @Query("SELECT c FROM CoinEntity c WHERE c.enabled = enabled")
    
    public List<CoinEntity> enabledList ( @Param(value = "id")boolean enabled);

    public CoinEntity findByIsoCode(CoinEntity oCode);
    
}
