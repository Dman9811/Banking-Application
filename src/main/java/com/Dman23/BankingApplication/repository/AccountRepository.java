package com.Dman23.BankingApplication.repository;

import com.Dman23.BankingApplication.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository <Account, Long>{
}
