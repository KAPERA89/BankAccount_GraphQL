package com.example.bankaccountservice.services;

import com.example.bankaccountservice.dtos.BankAccountDTOResponse;
import com.example.bankaccountservice.dtos.BankAccountRequestDTO;
import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.mappers.AccountMapper;
import com.example.bankaccountservice.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    BankAccountRepository bankAccountRepository;
    AccountMapper accountMapper;

    @Override
    public BankAccountDTOResponse AddAccount(BankAccountRequestDTO bankAccountDTO) {

        BankAccount bankAccount = accountMapper.fromBankAccountRequestDTO(bankAccountDTO);
        bankAccount.setId(UUID.randomUUID().toString());
        BankAccount savedBankAccount =  bankAccountRepository.save(bankAccount);
        BankAccountDTOResponse bankAccountDTOResponse =  accountMapper.fromBankAccount(savedBankAccount);
       return bankAccountDTOResponse;

    }

    @Override
    public BankAccountDTOResponse updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = accountMapper.fromBankAccountRequestDTO(bankAccountDTO);
        bankAccount.setId(id);
        BankAccount savedBankAccount =  bankAccountRepository.save(bankAccount);
        BankAccountDTOResponse bankAccountDTOResponse =  accountMapper.fromBankAccount(savedBankAccount);
        return bankAccountDTOResponse;

    }
}
