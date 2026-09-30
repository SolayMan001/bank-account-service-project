package org.sid.bank_account_service.web;

import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.service.AccountService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {

    private final AccountService accounts;

    public BankAccountGraphQLController(AccountService accounts) {
        this.accounts = accounts;
    }

    @QueryMapping
    public List<BankAccountResponseDTO> accountsList() {
        return accounts.findAll();
    }

    @SchemaMapping(typeName = "BankAccount")
    public long createdAt(BankAccountResponseDTO account) {
        return account.createdAt().getTime();
    }
}
