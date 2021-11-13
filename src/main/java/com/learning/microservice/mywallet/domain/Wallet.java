package com.learning.microservice.mywallet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@RequiredArgsConstructor
public class Wallet {
    private Long id;
    private Double balance;
    private List<Transaction> transactions;
}
