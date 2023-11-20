package com.amira.mibanquito.mod.bank.model;

import com.amira.mibanquito.mod.coin.model.CoinEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, String> {

    public BankEntity findBySwiftCode(String swiftCode);

    public List<BankEntity> findAllByEnabled(boolean enable);

    @Query("SELECT c FROM BankEntity c WHERE c.enabled = enabled")
    public List<BankEntity> enabledList(@Param(value = "id") boolean enabled);

}
