package com.example.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SystemAccounts")
public class SystemAccount {
    @Id
    private Integer accountId;
    private String username;
    private String email;
    private String password;
    private Integer role;
    private Boolean isActive;

    public SystemAccount() {
    }

    public SystemAccount(Integer accountId, String username, String email, String password, Integer role,
            Boolean isActive) {
        this.accountId = accountId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }

    // getters and setters
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
