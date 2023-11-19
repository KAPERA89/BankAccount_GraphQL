package com.example.bankaccountservice.web;

import com.example.bankaccountservice.dtos.BankAccountDTOResponse;
import com.example.bankaccountservice.dtos.BankAccountRequestDTO;
import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.mappers.AccountMapper;
import com.example.bankaccountservice.repositories.BankAccountRepository;
import com.example.bankaccountservice.services.AccountService;
import com.example.bankaccountservice.services.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AccountRestController {
   BankAccountRepository bankAccountRepository;
   AccountService accountService;
   AccountMapper accountMapper;

   @GetMapping("/bankAccounts")
   public List<BankAccountDTOResponse> bankAccounts(){
       List<BankAccount> bankAccountList = bankAccountRepository.findAll();
       return bankAccountList.stream().map(b-> accountMapper.fromBankAccount(b)).toList();
   }

    @GetMapping("/bankAccounts/{id}")
    public BankAccountDTOResponse bankAccount(@PathVariable String id){
        return accountMapper.fromBankAccount(bankAccountRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format("Account %s not found ",id))));
    }

    @PostMapping("/bankAccounts")
    public BankAccountDTOResponse save(@RequestBody BankAccountRequestDTO bankAccount){
        return accountService.AddAccount(bankAccount);
    }

    @PutMapping("/bankAccounts/{id}")
    public BankAccountDTOResponse update(@PathVariable String id, @RequestBody BankAccountRequestDTO bankAccount){
        BankAccount account=bankAccountRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format("Account %s not found ",id)));
        if(bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCurrency()!=null)account.setCurrency(bankAccount.getCurrency());
        /*if(bankAccount.getCreatedAt()!=null)*/ account.setCreatedAt(new Date());
        if (bankAccount.getType()!=null)account.setType(bankAccount.getType());


        return  accountMapper.fromBankAccount(bankAccountRepository.save(account));
    }

    @DeleteMapping("/bankAccounts/{id}")
    public void deletebankAccount(@PathVariable String id){
        bankAccountRepository.deleteById(id);
    }
}
