package com.learning.microservice.mywallet.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    private Long id;
    private Double balance;
    private List<Transaction> transactions;
}
