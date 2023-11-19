package com.example.bankaccountservice.services;

import com.example.bankaccountservice.dtos.BankAccountDTOResponse;
import com.example.bankaccountservice.dtos.BankAccountRequestDTO;
import org.springframework.stereotype.Service;

public interface AccountService {
    BankAccountDTOResponse AddAccount(BankAccountRequestDTO bankAccountDTO);
    BankAccountDTOResponse updateAccount(String id, BankAccountRequestDTO bankAccountDTO);
}
