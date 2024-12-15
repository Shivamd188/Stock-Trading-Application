package com.stocktrading.repository;

import com.stocktrading.model.TickerNSE;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TickerNSERepository extends JpaRepository<TickerNSE, String> {
    Page<TickerNSE> findByTickerNameContainingIgnoreCase(String tickerName, Pageable pageable);
}
