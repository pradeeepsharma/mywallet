package com.learning.microservice.mywallet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long transactionId;
    private double amount;
    private TransactionType transactionType;
    private Date date;


}
