package com.codingchallenge.reward.repository;

import com.codingchallenge.reward.model.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionInfoRepository extends JpaRepository<TransactionInfo, Integer> {
    @Query("SELECT TI FROM TransactionInfo TI WHERE customerId = :customerId and year(TI.date) = :year AND month(TI.date) = :month")
    List<TransactionInfo> findTransactionsForMonth(@Param("customerId") Long customerId, @Param("year") Integer year, @Param("month") Integer month);
}
