package org.sid.bank_account_service.web;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/bankAccounts")
public class AccountRestController {

    private final BankAccountRepository accounts;

    public AccountRestController(BankAccountRepository accounts) {
        this.accounts = accounts;
    }

    @GetMapping
    public List<BankAccount> findAll() {
        return accounts.findAll(Sort.by("id"));
    }

    @GetMapping("/{id}")
    public BankAccount findById(@PathVariable String id) {
        return accounts.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank account %s was not found".formatted(id))
        );
    }

    @PostMapping
    public ResponseEntity<BankAccount> save(@RequestBody BankAccount request) {
        BankAccount saved = accounts.save(new BankAccount(request.getBalance(), request.getCurrency(), request.getType()));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment(saved.getId())
                .build()
                .toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount request) {
        BankAccount account = findById(id);
        account.updateDetails(request.getBalance(), request.getCurrency(), request.getType());
        return accounts.save(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        accounts.delete(findById(id));
        return ResponseEntity.noContent().build();
    }
}
