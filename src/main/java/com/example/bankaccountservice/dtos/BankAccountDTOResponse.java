package com.example.bankaccountservice.dtos;

import com.example.bankaccountservice.enums.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccountDTOResponse {
    private String id;
    private Double balance;
    private Date createdAt;
    private String currency;
    private AccountType type;
}
