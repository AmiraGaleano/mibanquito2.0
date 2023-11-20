/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.coin.model;

import com.amira.mibanquito.mod.bank.model.BankEntity;
import com.amira.mibanquito.mod.coin.model.CoinEntity;
import com.amira.mibanquito.mod.coin.model.CoinRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CoinService {

   /* public static CoinEntity findByCoinIsoCode(String coinIsoCode) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
*/
    @Autowired
    private CoinRepository repository;

    public CoinRepository getRepository() {
        return repository;
    }

    public CoinEntity create(CoinEntity arg) {
        var coin = new CoinEntity();
        coin.setId(UUID.randomUUID().toString());
        coin.setCoin(arg.getCoin());
        return repository.save(coin);
    }
    public CoinEntity update(String id, CoinEntity arg) {
    var coin = repository.findById(id).get();
    coin.setCoin(arg.getCoin());
    return repository.save(coin);
  }

  public void delete(String id) {
    var coin = repository.findById(id).get();
    repository.delete(coin);
  }

  public void enable(String id) {
    var coin = repository.findById(id).get();
    coin.setEnabled(true);
    repository.save(coin);
  }

  public void disable(String id) {
    var coin = repository.findById(id).get();
    coin.setEnabled(false);
    repository.save(coin);
  }
  public CoinEntity findByIsoCode(String isoCode) {
      if (isoCode==null){
        return null;
      }
      return repository.findByIsoCode(isoCode);
    }

   
}
