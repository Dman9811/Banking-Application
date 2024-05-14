package com.Dman23.BankingApplication.service;

import com.Dman23.BankingApplication.entity.Account;

import java.util.List;

public interface AccountService {
    public Account createAccount(Account account);
    public Account getAccountDetailsByAccountNumber(Long accountNumber);
    public List<Account> getAllAccountsDetails();
    public Account depositAmount(Long accountNumber, Double amount);
    public Account withdrawAccount(Long accountNumber, Double amount);
    public void closeAccount(Long accountNumber);
}
