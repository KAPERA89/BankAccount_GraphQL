package com.example.bankaccountservice.repositories;

import com.example.bankaccountservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
