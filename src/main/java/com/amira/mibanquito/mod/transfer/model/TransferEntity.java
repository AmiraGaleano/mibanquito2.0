/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.transfer.model;

/**
 *
 * @author Bootcamp-1
 */
import com.amira.mibanquito.mod.movement.model.MovementEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class TransferEntity {

    @Id
    protected String id;
    protected String pid;

    protected String coinIsoCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date dateTime;
    @ManyToOne
    protected MovementEntity transfer;
    protected boolean successful;
    protected Integer errorCode;
    protected String concept;
    protected String senderDoc;
    protected String senderSwift;
    protected String senderIban;
    protected String senderTxId;
    protected String receiverDoc;
    protected String receiverSwift;
    protected String receiverIban;
    protected String receiverTxId;
    protected boolean enabled;
    protected BigDecimal amount;

    protected Date inserted;
    protected String inserter;
    protected Date modified;
    protected String modifier;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal Amount) {
        this.amount = Amount;

    }

    public String getCoinIsoCode() {
        return coinIsoCode;
    }

    public void setCoinIsoCode(String coinIsoCode) {
        this.coinIsoCode = coinIsoCode;

    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getSenderDoc() {
        return senderDoc;
    }

    public void setSenderDoc(String senderDoc) {
        this.senderDoc = senderDoc;
    }
    public MovementEntity getTransfer() {
        return transfer;
    }

    public void setTransfer(MovementEntity transfer) {
        this.transfer = transfer;
    }

    public String getReceivertDoc() {
        return receiverDoc;
    }

    public void setReceiverDoc(String receiverDoc) {
        this.receiverDoc = receiverDoc;
    }

    public String getSenderSwift() {
        return senderSwift;
    }

    public void setSenderSwift(String senderswift) {
        this.senderSwift = senderswift;
    }

    public String getReceiverSwift() {
        return receiverSwift;
    }

    public void setReceiverSwift(String receiverswift) {
        this.receiverSwift = receiverswift;
    }

    public String getSenderIban() {
        return senderIban;
    }

    public void setSenderIban(String senderiban) {
        this.senderIban = senderiban;
    }

    public String getReceiverIban() {
        return receiverIban;
    }

    public void setReceiverIban(String receiveriban) {
        this.receiverIban = receiveriban;
    }
  public String getSenderTxId() {
        return senderTxId;
    }

    public void setSenderTxId(String senderTxId) {
        this.senderTxId = senderTxId;
    }
  public String getReceiverTxId() {
        return receiverTxId;
    }

    public void setReceiverTxId(String receiverTxId) {
        this.receiverTxId = receiverTxId;
    }

   
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getInserted() {
        return inserted;
    }

    public void setInserted(Date inserted) {
        this.inserted = inserted;
    }

    public String getInserter() {
        return inserter;
    }

    public void setInserter(String inserter) {
        this.inserter = inserter;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public boolean getSuccessful() {
        return successful;

    }

    public void setSuccessful(Boolean successful) {
        this.successful = true;
        if (successful) {
            System.out.println("transaccion exitosa");
        } else {
            System.out.println("transaccion fallida");
        }
    }

    public Date getDateTime() {
        return new Date();

    }

    public void setDateTime(Date dateTime) {
        this.dateTime = new Date();
        System.out.println(this.dateTime);

    }
}
