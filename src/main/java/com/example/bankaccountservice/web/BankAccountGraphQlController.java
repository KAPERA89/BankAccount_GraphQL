package com.example.bankaccountservice.web;

import com.example.bankaccountservice.dtos.BankAccountDTOResponse;
import com.example.bankaccountservice.dtos.BankAccountRequestDTO;
import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.entities.Customer;
import com.example.bankaccountservice.mappers.AccountMapper;
import com.example.bankaccountservice.repositories.BankAccountRepository;
import com.example.bankaccountservice.repositories.CustomerRepository;
import com.example.bankaccountservice.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class BankAccountGraphQlController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private CustomerRepository customerRepository;

    @QueryMapping
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount bankAccountById(@Argument String id){
        return bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not Found"));
    }

    @MutationMapping
    public BankAccountDTOResponse addBankAccount(@Argument BankAccountRequestDTO bankAccount){
        return accountService.AddAccount(bankAccount);
    }

    @MutationMapping
    public BankAccountDTOResponse updateBankAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(id,bankAccount);
    }

    @MutationMapping
    public Boolean deleteBankAccount(@Argument String id){
         bankAccountRepository.deleteById(id);
         return true;
    }

    @QueryMapping
    public List<Customer> customers(){
        return customerRepository.findAll();
    }
}
