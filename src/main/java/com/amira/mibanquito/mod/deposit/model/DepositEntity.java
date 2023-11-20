/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amira.mibanquito.mod.deposit.model;

//import com.amira.mibanquito.mod.deposit.model.endpoint*.;
import com.amira.mibanquito.mod.movement.model.MovementEntity;
import com.amira.mibanquito.mod.movement.model.OperationTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class DepositEntity {

    @Id
    protected String id;
    protected String pid;
 
    protected String ibanCode;
    protected String swiftCode;
    protected String isoCode;
    protected String doc;
    protected BigDecimal amount;
    protected String concept;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date dateTime;
    @ManyToOne
    protected MovementEntity deposit;
    protected boolean successful;
    protected boolean enabled;
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

    public String getIbanCode() {
        return ibanCode;
    }

    public void setIbanCode( String ibanCode) {
        this.ibanCode = ibanCode;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode( String isocode) {
        this.isoCode = isocode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
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
    public MovementEntity getDeposit() {
        return deposit;
    }

    public void setDeposit(MovementEntity deposit) {
        this. deposit = deposit;
    }

 

  }

