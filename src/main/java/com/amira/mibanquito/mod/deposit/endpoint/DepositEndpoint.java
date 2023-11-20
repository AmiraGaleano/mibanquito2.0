/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.deposit.endpoint;

import com.amira.mibanquito.mod.account.model.AccountEntity;
import com.amira.mibanquito.mod.account.model.AccountService;
import com.amira.mibanquito.mod.balance.model.BalanceEntity;
import com.amira.mibanquito.mod.balance.model.BalanceService;
import com.amira.mibanquito.mod.bank.model.BankEntity;
import com.amira.mibanquito.mod.bank.model.BankService;
import com.amira.mibanquito.mod.coin.model.CoinEntity;
import com.amira.mibanquito.mod.coin.model.CoinService;
import com.amira.mibanquito.mod.deposit.model.DepositEntity;
import com.amira.mibanquito.mod.deposit.model.DepositService;
import com.amira.mibanquito.mod.movement.model.MovementEntity;
import com.amira.mibanquito.mod.movement.model.MovementService;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deposit")
public class DepositEndpoint {

    private static final Logger logger = Logger.getLogger(DepositEndpoint.class.getName());
    @Autowired
    private DepositService service;
    @Autowired
    private BankService bankService;
    @Autowired
    private CoinService coinService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private MovementService movementService;

    @PostMapping
    public DepositEntity create(@RequestBody DepositEntity arg) {

        /*
        validar argumento
        
        obtener entidades necesarias
        validar existencia de entidades necesarias
        crear el deposito
        crear el movimento
        actualizar el saldo
        decir que somos felices
         */
        if (arg.getAmount().intValue() <= 0) {
            logger.info("mi monto es <0");
            return null;
        }

        var bank = findBank(arg.getSwiftCode());
        if (bank == null) {
            logger.info("no banco");
            return null;
        }
        var coin = findCoin(arg.getIsoCode());
        if (coin == null) {
            logger.info("moneda no");
            return null;
        }
        var account = findAccount(arg.getIbanCode());
        if (account == null) {
            logger.info("cuenta off");
            return null;
        }

        var balance = findBalance(account);
        if (balance == null) {
            return null;
        }
        if (!account.getCoin().equals(coin.getCoin())) {
            return null;
        }

        var deposit = service.create(arg);

        var movement = new MovementEntity();

        //hacer todos los set
        movement.setAmount(arg.getAmount());
        movement.setDeposit(deposit);
        movement.setAccount(account);

        movement = movementService.create(movement);

        var amount = balance.getAmount();

        BigDecimal newAmount = amount.add(deposit.getAmount());
        balance.setAmount(newAmount);
        balanceService.getRepository().save(balance);

        deposit.setSuccessful(Boolean.TRUE);
        logger.info("todo salio bien");
        return deposit;
    }

    @GetMapping
    public void list() {

    }

    @GetMapping("/{id}")
    public DepositEntity find(@PathVariable("id") String id) {
        return service.getRepository().findById(id).get();
    }

    @PutMapping("/{id}")
    public DepositEntity update(@PathVariable("id") String id, @RequestBody DepositEntity arg) {
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
    //protected 

    private BankEntity findBank(String swift) {

        return bankService.findBySwiftCode(swift);

    }

    private CoinEntity findCoin(String coinIsoCode) {

        return coinService.findByIsoCode(coinIsoCode);

    }

    private AccountEntity findAccount(String iban) {

        return accountService.findByIbanCode(iban);

    }

    private BalanceEntity findBalance(AccountEntity account) {

        return balanceService.findByAccount(account);

    }

}
