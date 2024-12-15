package com.stocktrading.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocktrading.model.PortfolioStock;
import com.stocktrading.model.User;

@Repository
public interface PortfolioStockRepository extends JpaRepository<PortfolioStock, Long> {
    List<PortfolioStock> findByUser(User user);
    List<PortfolioStock> findByUserId(Long userId);
}


