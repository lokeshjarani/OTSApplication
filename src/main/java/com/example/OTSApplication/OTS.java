package com.example.OTSApplication;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
public class

OTS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accounts;
    private String customerName;
    private BigDecimal otsAmount;
    private LocalDate sanctionDate;
    private LocalDate expiryDate;
    private String updatedBy;
    private Timestamp creationTime;

    public Long getId() {
        return id;
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts;
    }

    public String getAccounts() {
        return accounts;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getOtsAmount() {
        return otsAmount;
    }

    public void setOtsAmount(BigDecimal otsAmount) {
        this.otsAmount = otsAmount;
    }

    public LocalDate getSanctionDate() {
        return sanctionDate;
    }

    public void setSanctionDate(LocalDate sanctionDate) {
        this.sanctionDate = sanctionDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}



