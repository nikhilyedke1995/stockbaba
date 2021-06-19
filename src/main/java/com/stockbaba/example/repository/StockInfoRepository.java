package com.stockbaba.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockbaba.example.entity.StockInfo;

@Repository
public interface StockInfoRepository extends JpaRepository<StockInfo, Long>{
	
	List<StockInfo> findByNameContainingIgnoreCase(String infix);
}
