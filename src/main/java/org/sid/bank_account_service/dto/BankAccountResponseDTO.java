package org.sid.bank_account_service.dto;

import org.sid.bank_account_service.entities.Customer;
import org.sid.bank_account_service.enums.AccountType;

import java.util.Date;

public record BankAccountResponseDTO(
        String id,
        Date createdAt,
        Double balance,
        String currency,
        AccountType type,
        Customer customer
) {
}
