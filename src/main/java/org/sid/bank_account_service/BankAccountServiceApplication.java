package org.sid.bank_account_service;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.entities.Customer;
import org.sid.bank_account_service.enums.AccountType;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.repositories.CustomerRepository;
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
    CommandLineRunner initializeAccounts(BankAccountRepository accounts, CustomerRepository customers) {
        return args -> {
            Customer amina = customers.save(new Customer("Amina El Mansouri"));
            Customer omar = customers.save(new Customer("Omar Amrani"));

            BankAccount everyday = new BankAccount(2450.00, "MAD", AccountType.CURRENT_ACCOUNT);
            everyday.setCustomer(amina);
            BankAccount savings = new BankAccount(6850.50, "MAD", AccountType.SAVING_ACCOUNT);
            savings.setCustomer(amina);
            BankAccount euros = new BankAccount(1200.00, "EUR", AccountType.CURRENT_ACCOUNT);
            euros.setCustomer(omar);

            accounts.saveAll(List.of(everyday, savings, euros));
        };
    }
}
