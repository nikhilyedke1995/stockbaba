package com.stockbaba.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockbaba.example.entity.StockInfo;
import com.stockbaba.example.repository.StockInfoRepository;
import com.zerodhatech.kiteconnect.KiteConnect;

@RestController
@RequestMapping("/chart")
public class StockChartController {

	@Autowired
	private StockInfoRepository repo;
	
	@Autowired
	private KiteConnect connect;
	
	private Logger logger = LoggerFactory.getLogger(StockChartController.class);
	
	@GetMapping("/stock")
	public List<StockInfo> getStockChart(@RequestParam("stockName")String stockName){
		logger.info("inside controller for stockName " + stockName);
		List<StockInfo> list = repo.findByNameContainingIgnoreCase(stockName);
		logger.info("result list size " + list.size());
		return list;
	}

}
