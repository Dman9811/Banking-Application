package com.Dman23.BankingApplication.service;

import com.Dman23.BankingApplication.entity.Account;
import com.Dman23.BankingApplication.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        Account account_saved = accountRepository.save(account);
        return account_saved;
    }

    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if(account.isEmpty()){
            throw new RuntimeException("Account does not exists");
        }
        Account fetchedAccount = account.get();
        return fetchedAccount;
    }

    @Override
    public List<Account> getAllAccountsDetails() {
        List<Account> listOfAccounts = accountRepository.findAll();
        return listOfAccounts;
    }

    @Override
    public Account depositAmount(Long accountNumber, Double amount) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if(account.isEmpty()){
            throw new RuntimeException("Account does not exists");
        }
        if(amount < 0.99){
            throw new RuntimeException("Deposit amount must be greater than $0.99");
        }
        Account depositAccount = account.get();
        Double total_balance = depositAccount.getAccount_balance() + amount;
        depositAccount.setAccount_balance(total_balance);

        accountRepository.save(depositAccount);

        return depositAccount;
    }

    @Override
    public Account withdrawAccount(Long accountNumber, Double amount) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        if(account.isEmpty()){
            throw new RuntimeException("Account does not exists");
        }
        if(amount < 0.99){
            throw new RuntimeException("Withdraw amount must be greater than $0.99");
        }
        Account withdrawAccount = account.get();
        if(withdrawAccount.getAccount_balance() < amount){
            throw new RuntimeException("Withdraw amount cannot be greater than current balance");
        }
        Double total_balance = withdrawAccount.getAccount_balance() - amount;
        withdrawAccount.setAccount_balance(total_balance);

        accountRepository.save(withdrawAccount);

        return withdrawAccount;
    }

    @Override
    public void closeAccount(Long accountNumber) {
        getAccountDetailsByAccountNumber(accountNumber);
        accountRepository.deleteById(accountNumber);
    }
}
