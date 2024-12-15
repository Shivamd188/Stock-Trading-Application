package com.stocktrading.service;

import com.stocktrading.model.Transaction;
import com.stocktrading.model.User;
import com.stocktrading.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getRecentTransactions(User user) {
        return transactionRepository.findTop5ByUserOrderByDateDesc(user);
    }
}
