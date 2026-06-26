package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    // Get all FB stocks in September 2019
    List<Stock> findByCodeAndDateBetween(String code, LocalDate startDate, LocalDate endDate);

    // Get GOOGL stocks where close > 1250
    List<Stock> findByCodeAndCloseGreaterThan(String code, BigDecimal price);

    // Find top 3 by highest volume
    List<Stock> findTop3ByOrderByVolumeDesc();
}
