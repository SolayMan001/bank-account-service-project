package org.sid.bank_account_service;

import org.junit.jupiter.api.Test;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.enums.AccountType;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BankAccountServiceApplicationTests {

    @Autowired
    private BankAccountRepository bankAccounts;

    @Test
    void initializesSampleAccountsInH2() {
        assertThat(bankAccounts.findAll()).hasSize(3)
                .allSatisfy(account -> {
                    assertThat(account.getId()).isNotBlank();
                    assertThat(account.getCreatedAt()).isNotNull();
                })
                .extracting(BankAccount::getType)
                .containsExactlyInAnyOrder(
                        AccountType.CURRENT_ACCOUNT, AccountType.SAVING_ACCOUNT, AccountType.CURRENT_ACCOUNT
                );
    }
}
