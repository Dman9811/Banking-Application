package com.Dman23.BankingApplication.controller;

import com.Dman23.BankingApplication.entity.Account;
import com.Dman23.BankingApplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService service;

    //create bank account
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account account_created = service.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(account_created);
    }

    //get account details from account number
    @GetMapping("/{accountNumber}")
    public Account getAccountByAccountNumber(@PathVariable Long accountNumber){
        Account accountDetails = service.getAccountDetailsByAccountNumber(accountNumber);
        return accountDetails;
    }

    //get all accounts
    @GetMapping("/getallaccounts")
    public List<Account> getAllAccountsDetails(){
        List<Account> fetched_accounts = service.getAllAccountsDetails();
        return fetched_accounts;
    }

    //deposit money to account
    @PutMapping("/deposit/{accountNumber}/{amount}")
    public Account depositAmount(@PathVariable Long accountNumber,@PathVariable Double amount){
        Account depositAccount = service.depositAmount(accountNumber, amount);
        return depositAccount;
    }

    //withdraw money from account
    @PutMapping("/withdraw/{accountNumber}/{amount}")
    public Account withdrawAccount(@PathVariable Long accountNumber,@PathVariable Double amount){
        Account withdrawAccount = service.withdrawAccount(accountNumber, amount);
        return withdrawAccount;
    }

    //delete account
    @DeleteMapping("/delete/{accountNumber}")
    public ResponseEntity<String> closeAccount(@PathVariable Long accountNumber){
        service.closeAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account closed");
    }
}
