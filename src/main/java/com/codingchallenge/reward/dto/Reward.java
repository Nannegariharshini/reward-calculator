package com.codingchallenge.reward.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reward {
    private Long customerId;
    private Long monthOneReward;
    private Long monthTwoReward;
    private Long monthThreeReward;
    private Long totalReward;
}
