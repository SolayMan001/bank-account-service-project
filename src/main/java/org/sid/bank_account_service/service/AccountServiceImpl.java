package org.sid.bank_account_service.service;

import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.mappers.AccountMapper;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final BankAccountRepository accounts;
    private final AccountMapper mapper;

    public AccountServiceImpl(BankAccountRepository accounts, AccountMapper mapper) {
        this.accounts = accounts;
        this.mapper = mapper;
    }

    @Override
    public List<BankAccountResponseDTO> findAll() {
        return accounts.findAll(Sort.by("id")).stream().map(mapper::toResponse).toList();
    }

    @Override
    public BankAccountResponseDTO findById(String id) {
        return mapper.toResponse(requireAccount(id));
    }

    @Override
    @Transactional
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO request) {
        requireRequest(request);
        return mapper.toResponse(accounts.save(mapper.toAccount(request)));
    }

    @Override
    @Transactional
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO request) {
        requireRequest(request);
        BankAccount account = requireAccount(id);
        account.updateDetails(request.balance(), request.currency(), request.type());
        return mapper.toResponse(account);
    }

    @Override
    @Transactional
    public boolean deleteAccount(String id) {
        accounts.delete(requireAccount(id));
        return true;
    }

    private BankAccount requireAccount(String id) {
        if (id == null || id.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A bank account ID is required");
        }
        return accounts.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank account %s was not found".formatted(id))
        );
    }

    private void requireRequest(BankAccountRequestDTO request) {
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bank account information is required");
        }
    }
}
