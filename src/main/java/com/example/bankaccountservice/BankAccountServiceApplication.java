package com.example.bankaccountservice;

import com.example.bankaccountservice.entities.BankAccount;
import com.example.bankaccountservice.entities.Customer;
import com.example.bankaccountservice.enums.AccountType;
import com.example.bankaccountservice.repositories.BankAccountRepository;
import com.example.bankaccountservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
        return args -> {

            Stream.of("Othmane","Darhoni","Kapera").forEach(c -> {
                customerRepository.save(Customer.builder().name(c).build());
            });

            customerRepository.findAll().forEach(c -> {
                for (int i = 0; i < 10; i++) {
                    BankAccount bankAccount = BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                            .balance(10000 + Math.random() * 90000)
                            .currency("MAD")
                            .createdAt(new Date())
                            .customer(c)
                            .build();

                    bankAccountRepository.save(bankAccount);
                }
            });


        };
    }
}
