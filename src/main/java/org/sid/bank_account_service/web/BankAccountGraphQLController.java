package org.sid.bank_account_service.web;

import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.Customer;
import org.sid.bank_account_service.mappers.AccountMapper;
import org.sid.bank_account_service.repositories.CustomerRepository;
import org.sid.bank_account_service.service.AccountService;
import org.springframework.data.domain.Sort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
public class BankAccountGraphQLController {

    private final AccountService accounts;

    private final CustomerRepository customers;
    private final AccountMapper mapper;

    public BankAccountGraphQLController(
            AccountService accounts, CustomerRepository customers, AccountMapper mapper
    ) {
        this.accounts = accounts;
        this.customers = customers;
        this.mapper = mapper;
    }

    @QueryMapping
    public List<BankAccountResponseDTO> accountsList() {
        return accounts.findAll();
    }

    @QueryMapping
    public BankAccountResponseDTO bankAccountById(@Argument String id) {
        return accounts.findById(id);
    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount) {
        return accounts.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccount) {
        return accounts.updateAccount(id, bankAccount);
    }

    @MutationMapping
    public boolean deleteAccount(@Argument String id) {
        return accounts.deleteAccount(id);
    }

    @QueryMapping
    public List<Customer> customers() {
        return customers.findAll(Sort.by("id"));
    }

    @SchemaMapping(typeName = "Customer")
    @Transactional(readOnly = true)
    public List<BankAccountResponseDTO> bankAccounts(Customer customer) {
        Customer storedCustomer = customers.getReferenceById(customer.getId());
        return storedCustomer.getBankAccounts().stream().map(mapper::toResponse).toList();
    }

    @SchemaMapping(typeName = "BankAccount")
    public long createdAt(BankAccountResponseDTO account) {
        return account.createdAt().getTime();
    }
}
