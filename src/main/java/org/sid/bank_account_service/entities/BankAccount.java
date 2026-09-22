package org.sid.bank_account_service.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import org.sid.bank_account_service.enums.AccountType;

import java.util.Date;

@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, updatable = false)
    private Date createdAt;

    private Double balance;

    private String currency;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    protected BankAccount() {
    }

    public BankAccount(Double balance, String currency, AccountType type) {
        this.balance = balance;
        this.currency = currency;
        this.type = type;
    }

    @PrePersist
    void setCreationDate() {
        createdAt = new Date();
    }

    public String getId() {
        return id;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Date getCreatedAt() {
        return createdAt;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
