package org.sid.bank_account_service;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.enums.AccountType;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initializeAccounts(BankAccountRepository accounts) {
        return args -> accounts.saveAll(List.of(
                new BankAccount(2450.00, "MAD", AccountType.CURRENT_ACCOUNT),
                new BankAccount(6850.50, "MAD", AccountType.SAVING_ACCOUNT),
                new BankAccount(1200.00, "EUR", AccountType.CURRENT_ACCOUNT)
        ));
    }
}
