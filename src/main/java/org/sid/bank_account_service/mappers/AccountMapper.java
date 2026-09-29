package org.sid.bank_account_service.mappers;

import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public BankAccount toAccount(BankAccountRequestDTO request) {
        return new BankAccount(request.balance(), request.currency(), request.type());
    }

    public BankAccountResponseDTO toResponse(BankAccount account) {
        return new BankAccountResponseDTO(
                account.getId(),
                account.getCreatedAt(),
                account.getBalance(),
                account.getCurrency(),
                account.getType()
        );
    }
}
