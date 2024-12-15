package com.stocktrading.repository;

import com.stocktrading.model.Transaction;
import com.stocktrading.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTop5ByUserOrderByDateDesc(User user);
}
