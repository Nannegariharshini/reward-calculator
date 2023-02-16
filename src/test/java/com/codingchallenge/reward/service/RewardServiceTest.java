package com.codingchallenge.reward.service;

import com.codingchallenge.reward.dto.Reward;
import com.codingchallenge.reward.model.TransactionInfo;
import com.codingchallenge.reward.repository.TransactionInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RewardServiceTest {

    @InjectMocks
    RewardService rewardService;
    @Mock
    TransactionInfoRepository transactionInfoRepository;


    @Test
    public void test_buildAndGetRewardOfCustomer() {

        List<TransactionInfo> transactionList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();


        TransactionInfo t1 = new TransactionInfo();
        t1.setCustomerId(1L);
        t1.setAmount(120);
        t1.setTransactionId(1L);
        t1.setDate(Timestamp.valueOf(now));

        transactionList.add(t1);

        when(transactionInfoRepository.findTransactionsForMonth(anyLong(), anyInt(), anyInt())).thenReturn(transactionList);

        Reward rewards = rewardService.buildAndGetRewardOfCustomer(1L);
        assertThat(rewards).isNotNull();

        //90+90+90
        assertThat(rewards.getTotalReward()).isEqualTo(270L);

    }

    @Test
    public void test_buildAndGetRewardOfCustomer2() {

        List<TransactionInfo> transactionList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();


        TransactionInfo t1 = new TransactionInfo();
        t1.setCustomerId(1L);
        t1.setAmount(110);
        t1.setTransactionId(1L);
        t1.setDate(Timestamp.valueOf(now));

        transactionList.add(t1);

        when(transactionInfoRepository.findTransactionsForMonth(anyLong(), anyInt(), anyInt())).thenReturn(transactionList);

        Reward rewards = rewardService.buildAndGetRewardOfCustomer(1L);
        assertThat(rewards).isNotNull();

        //70+70+70
        assertThat(rewards.getTotalReward()).isEqualTo(210L);

    }

}