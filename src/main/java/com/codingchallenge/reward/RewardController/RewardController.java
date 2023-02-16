package com.codingchallenge.reward.RewardController;


import com.codingchallenge.reward.dto.CustomerTransaction;
import com.codingchallenge.reward.dto.Reward;
import com.codingchallenge.reward.service.RewardService;
import com.codingchallenge.reward.service.TransactionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reward")
public class RewardController {

    @Autowired
    TransactionInfoService transactionInfoService;

    @Autowired
    RewardService rewardService;

    @PostMapping("/transaction")
    public ResponseEntity<String> addTransaction(@RequestBody CustomerTransaction transaction) {
        transactionInfoService.addTransaction(transaction);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @GetMapping("/{customerId}")
    public ResponseEntity<Reward> buildAndGetRewardOfCustomer(@PathVariable Long customerId) {
        Reward rewards = rewardService.buildAndGetRewardOfCustomer(customerId);
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }
}
