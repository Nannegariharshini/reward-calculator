package com.codingchallenge.reward.repository;

import com.codingchallenge.reward.model.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionInfoRepository extends JpaRepository<TransactionInfo, Integer> {
}
