package com.codingchallenge.reward.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerTransaction {
    private Long customerId;
    private double amount;
}
