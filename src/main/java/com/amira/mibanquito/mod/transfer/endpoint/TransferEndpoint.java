/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.transfer.endpoint;

import com.amira.mibanquito.mod.account.model.AccountEntity;
import com.amira.mibanquito.mod.account.model.AccountService;
import com.amira.mibanquito.mod.balance.model.BalanceEntity;
import com.amira.mibanquito.mod.balance.model.BalanceService;
import com.amira.mibanquito.mod.bank.model.BankEntity;
import com.amira.mibanquito.mod.bank.model.BankService;
import com.amira.mibanquito.mod.coin.model.CoinEntity;
import com.amira.mibanquito.mod.coin.model.CoinService;
import com.amira.mibanquito.mod.deposit.endpoint.DepositEndpoint;
import com.amira.mibanquito.mod.movement.model.MovementEntity;
import com.amira.mibanquito.mod.movement.model.MovementService;
import com.amira.mibanquito.mod.transfer.model.TransferEntity;
import com.amira.mibanquito.mod.transfer.model.TransferService;
import java.math.BigDecimal;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping("/transfer")
public class TransferEndpoint {

    private static final Logger logger = Logger.getLogger(DepositEndpoint.class.getName());
    @Autowired
    private TransferService service;
    @Autowired
    private BankService bankService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CoinService coinService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private MovementService movementService;

    @PostMapping
    public TransferEntity create(@RequestBody TransferEntity arg) {
        /*
        validar argumento
        obtener entidades necesarias
        validar existencia de entidades necesarias
        crear la transferencia
        crear el movimento
        actualizar el saldo      
        decir que somos felices
         */

        if (arg.getAmount().intValue() <= 0) {
            logger.info("mi monto es <0");
            return null;
        }

        var senderBank = findBank(arg.getSenderSwift());
        if (senderBank == null) {
            logger.info("banco emisor off");
            return null;
        }

        var receiverBank = findBank(arg.getReceiverSwift());
        if (receiverBank == null) {
            logger.info("banco receptor off ");
            return null;
        }

        if (senderBank.equals(receiverBank) && senderBank.getSwift().equals("CódigoSWIFT")) {
            return transferLocal(arg, senderBank);
        } else if (receiverBank.getSwift().equals("CódigoSWIFT")) {
            return transferReceive(arg, senderBank, receiverBank);
        } else if (senderBank.getSwift().equals("CódigoSWIFT")) {
            return (transferSend(arg, senderBank, receiverBank));
        }
        return null;
    }

    private TransferEntity transferSend(TransferEntity arg, BankEntity bank, BankEntity receiverBank) {
        var coin = findCoin(arg.getCoinIsoCode());
        if (coin == null) {
            return null;
        }
        var senderAccount = findAccount(arg.getSenderIban());
        if (senderAccount == null) {
            return null;
        }
        var senderBalance = findBalance(senderAccount);
        if (senderBalance == null) {
            return null;
        }
        if (!senderAccount.getCoin().equals(coin.getCoin())) {
            return null;
        }

        var transfer = service.create(arg);

        transfer.setSenderTxId(transfer.getId());

        var senderMovement = new MovementEntity();
        senderMovement.setAmount(arg.getAmount());
        senderMovement.setTransfer(transfer);
        senderMovement.setAccount(senderAccount);

        senderMovement = movementService.create(senderMovement);

        var senderAmount = senderBalance.getAmount();

        BigDecimal newSenderAmount = senderAmount.subtract(transfer.getAmount());
        senderBalance.setAmount(newSenderAmount);
        balanceService.getRepository().save(senderBalance);
        // aca va el envio
        String url = "https://servidor-externo.com/api/transferir";

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, null, String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                transfer.setSuccessful(Boolean.TRUE);

            }
        } catch (Throwable t) {
            transfer.setSuccessful(Boolean.FALSE);

        }

        service.getRepository().save(transfer);
        return transfer;

    }

    private TransferEntity transferReceive(TransferEntity arg, BankEntity bank, BankEntity receiverBank) {
        var coin = findCoin(arg.getCoinIsoCode());
        if (coin == null) {
            return null;
        }

        var receiverAccount = findAccount(arg.getReceiverIban());
        if (receiverAccount == null) {
            return null;
        }
        var receiverBalance = findBalance(receiverAccount);
        if (receiverBalance == null) {
            return null;
        }

        var transfer = service.create(arg);
//si hace falta validar la moneda del balance receiver
        transfer.setReceiverTxId(transfer.getId());
        transfer.setSenderTxId(transfer.getId());

        var receiverMovement = new MovementEntity();

        receiverMovement.setAmount(arg.getAmount());
        receiverMovement.setTransfer(transfer);
        receiverMovement.setAccount(receiverAccount);

        receiverMovement = movementService.create(receiverMovement);

        var receiverAmount = receiverBalance.getAmount();

        BigDecimal newReceiverAmount = receiverAmount.add(transfer.getAmount());
        receiverBalance.setAmount(newReceiverAmount);
        balanceService.getRepository().save(receiverBalance);

        transfer.setSuccessful(Boolean.TRUE);
        service.getRepository().save(transfer);
        return transfer;

    }

    private TransferEntity transferLocal(TransferEntity arg, BankEntity bank) {
        var coin = findCoin(arg.getCoinIsoCode());
        if (coin == null) {
            return null;
        }

        var senderAccount = findAccount(arg.getSenderIban());
        if (senderAccount == null) {

            return null;
        }

        var senderBalance = findBalance(senderAccount);
        if (senderBalance == null) {
            return null;
        }

        var receiverAccount = findAccount(arg.getReceiverIban());
        if (receiverAccount == null) {
            return null;
        }
        var receiverBalance = findBalance(receiverAccount);
        if (receiverBalance == null) {
            return null;
        }
        if (!senderAccount.getCoin().equals(coin.getCoin())) {
            return null;
        }
        // COMO hacer id de los dos  

        var transfer = service.create(arg);

        var receiverMovement = new MovementEntity();
        var senderMovement = new MovementEntity();

        receiverMovement.setAmount(arg.getAmount());
        receiverMovement.setTransfer(transfer);
        receiverMovement.setAccount(receiverAccount);

        receiverMovement = movementService.create(receiverMovement);

        var receiverAmount = receiverBalance.getAmount();

        BigDecimal newReceiverAmount = receiverAmount.add(transfer.getAmount());
        receiverBalance.setAmount(newReceiverAmount);
        balanceService.getRepository().save(receiverBalance);

        senderMovement.setAmount(arg.getAmount());
        senderMovement.setTransfer(transfer);
        senderMovement.setAccount(senderAccount);

        senderMovement = movementService.create(senderMovement);

        var senderAmount = senderBalance.getAmount();

        BigDecimal newSenderAmount = senderAmount.subtract(transfer.getAmount());
        senderBalance.setAmount(newSenderAmount);
        balanceService.getRepository().save(senderBalance);

        transfer.setSuccessful(Boolean.TRUE);

        service.getRepository().save(transfer);
        return transfer;

    }

    @GetMapping
    public void list() {

    }

    @GetMapping("/{id}")
    public TransferEntity find(@PathVariable("id") String id) {
        return service.getRepository().findById(id).get();

    }

    @PutMapping("/{id}")
    public TransferEntity update(@PathVariable("id") String id, @RequestBody TransferEntity arg) {
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
