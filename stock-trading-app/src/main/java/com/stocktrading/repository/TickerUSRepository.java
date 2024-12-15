package com.stocktrading.repository;

import com.stocktrading.model.TickerUS;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TickerUSRepository extends JpaRepository<TickerUS, String> {
    Page<TickerUS> findByTickerNameContainingIgnoreCase(String tickerName, Pageable pageable);
}
