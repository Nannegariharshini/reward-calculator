package com.codingchallenge.reward.service;


import com.codingchallenge.reward.dto.Reward;
import com.codingchallenge.reward.model.TransactionInfo;
import com.codingchallenge.reward.repository.TransactionInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RewardService {

    public static final int ABOVE_50_BELOW_100 = 50;
    public static final int OVER_1OO = 100;
    public static final int ABOVE_50_BELOW_100_POINTS = 1;
    public static final int OVER_1OO_POINTS = 2;

    @Autowired
    private TransactionInfoRepository transactionInfoRepository;

    public Reward buildAndGetRewardOfCustomer(Long customerId) {

        LocalDateTime presentDateTime = LocalDateTime.now();

        //Get all transactions of present month
        List<TransactionInfo> presentMonthTransactions = transactionInfoRepository.findTransactionsForMonth(customerId, presentDateTime.getYear(), presentDateTime.getMonthValue());

        //Get all transactions of last month
        LocalDateTime lastMonthDateTime = presentDateTime.minusMonths(1);
        List<TransactionInfo> lastMonthTransactions = transactionInfoRepository.findTransactionsForMonth(customerId, lastMonthDateTime.getYear(), lastMonthDateTime.getMonthValue());

        //Get all transactions of the month before last month
        LocalDateTime monthBeforeLastMonthDateTime = presentDateTime.minusMonths(1);
        List<TransactionInfo> monthBeforeLastMonthTransactions = transactionInfoRepository.findTransactionsForMonth(customerId, monthBeforeLastMonthDateTime.getYear(), monthBeforeLastMonthDateTime.getMonthValue());

        Long presentMonthRewardPoints = calculateRewardsForTransactions(presentMonthTransactions);
        Long lastMonthRewardPoints = calculateRewardsForTransactions(lastMonthTransactions);
        Long monthBeforeLastMonthMonthRewardPoints = calculateRewardsForTransactions(monthBeforeLastMonthTransactions);
        Long totalRewardPoints = presentMonthRewardPoints + lastMonthRewardPoints + monthBeforeLastMonthMonthRewardPoints;

        return new Reward(customerId, presentMonthRewardPoints, lastMonthRewardPoints, monthBeforeLastMonthMonthRewardPoints, totalRewardPoints);


    }


    private Long calculateRewardsForTransactions(List<TransactionInfo> transactions) {
        return transactions.stream().map(this::getRewardsOfTransaction).mapToLong(r -> r).sum();
    }

    private Long getRewardsOfTransaction(TransactionInfo txn) {
        double amount = txn.getAmount();
        if (amount > ABOVE_50_BELOW_100 && amount < OVER_1OO)
            return Math.round(amount - ABOVE_50_BELOW_100) * ABOVE_50_BELOW_100_POINTS;
        else if (amount > OVER_1OO)
            return Math.round(amount - OVER_1OO) * OVER_1OO_POINTS
                    + (OVER_1OO - ABOVE_50_BELOW_100) * ABOVE_50_BELOW_100_POINTS;
        else
            return 0L;
    }
}
