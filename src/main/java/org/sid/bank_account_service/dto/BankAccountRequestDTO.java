package org.sid.bank_account_service.dto;

import org.sid.bank_account_service.enums.AccountType;

public record BankAccountRequestDTO(Double balance, String currency, AccountType type) {
}
