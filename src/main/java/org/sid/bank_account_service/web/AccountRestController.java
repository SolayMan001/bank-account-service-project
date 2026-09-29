package org.sid.bank_account_service.web;

import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.service.AccountService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bankAccounts")
public class AccountRestController {

    private final AccountService accounts;

    public AccountRestController(AccountService accounts) {
        this.accounts = accounts;
    }

    @GetMapping
    public List<BankAccountResponseDTO> findAll() {
        return accounts.findAll();
    }

    @GetMapping("/{id}")
    public BankAccountResponseDTO findById(@PathVariable String id) {
        return accounts.findById(id);
    }

    @PostMapping
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO request) {
        return accounts.addAccount(request);
    }

    @PutMapping("/{id}")
    public BankAccountResponseDTO update(@PathVariable String id, @RequestBody BankAccountRequestDTO request) {
        return accounts.updateAccount(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        accounts.deleteAccount(id);
    }
}
