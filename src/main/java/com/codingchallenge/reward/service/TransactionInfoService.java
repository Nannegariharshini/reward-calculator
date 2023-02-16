package com.codingchallenge.reward.service;

import com.codingchallenge.reward.dto.CustomerTransaction;
import com.codingchallenge.reward.model.TransactionInfo;
import com.codingchallenge.reward.repository.TransactionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Service
public class TransactionInfoService {
    @Autowired
    private TransactionInfoRepository transactionRepository;

    public void addTransaction(CustomerTransaction customerTransaction) {
        LocalDateTime dateTime = LocalDateTime.now();
        Timestamp currentTimestamp = Timestamp.valueOf(dateTime);

        TransactionInfo transactionInfo = new TransactionInfo();
        transactionInfo.setCustomerId(customerTransaction.getCustomerId());
        transactionInfo.setAmount(customerTransaction.getAmount());
        transactionInfo.setDate(currentTimestamp);
        transactionRepository.save(transactionInfo);
    }
}
