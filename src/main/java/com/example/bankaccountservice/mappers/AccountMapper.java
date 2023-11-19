package com.example.bankaccountservice.mappers;

import com.example.bankaccountservice.dtos.BankAccountDTOResponse;
import com.example.bankaccountservice.dtos.BankAccountRequestDTO;
import com.example.bankaccountservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {
    public BankAccountDTOResponse fromBankAccount(BankAccount bankAccount){
        BankAccountDTOResponse bankAccountDTOResponse = new BankAccountDTOResponse();
        BeanUtils.copyProperties(bankAccount, bankAccountDTOResponse);
        return bankAccountDTOResponse;
    }

    public BankAccount fromBankAccountRequestDTO(BankAccountRequestDTO bankAccountRequestDTO){
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountRequestDTO, bankAccount);
        return bankAccount;
    }
}
