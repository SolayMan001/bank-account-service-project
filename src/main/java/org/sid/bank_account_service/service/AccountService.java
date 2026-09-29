package org.sid.bank_account_service.service;

import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;

import java.util.List;

public interface AccountService {

    List<BankAccountResponseDTO> findAll();

    BankAccountResponseDTO findById(String id);

    BankAccountResponseDTO addAccount(BankAccountRequestDTO request);

    BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO request);

    boolean deleteAccount(String id);
}
