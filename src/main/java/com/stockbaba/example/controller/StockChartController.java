package com.stockbaba.example.controller;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;

import javax.management.ServiceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockbaba.example.entity.StockInfo;
import com.stockbaba.example.exception.StockDetailsException;
import com.stockbaba.example.exception.StockDetailsNotFoundException;
import com.stockbaba.example.repository.StockInfoRepository;
import com.stockbaba.example.service.StockDetailsService;
import com.stockbaba.example.util.KiteUtility;
import com.zerodhatech.models.HistoricalData;

@RestController
@RequestMapping("/chart")
public class StockChartController {

	@Autowired
	private StockInfoRepository repo;
	
	@Autowired
	private KiteUtility util;
	
	@Autowired
	private StockDetailsService service;
	
	private static final Logger logger = LoggerFactory.getLogger(StockChartController.class);
	
	@GetMapping("/stock")
	public ResponseEntity<List<StockInfo>> getStockChart(@RequestParam("stockName")String stockName){
		logger.info("inside controller for stockName " + stockName);
		List<StockInfo> list = repo.findByNameContainingIgnoreCase(stockName);
		logger.info("result list size " + list.size());
		if(list==null || list.size()==0)
			throw new StockDetailsNotFoundException("No details found for this query");
		return new ResponseEntity<List<StockInfo>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/stockDetails")
	public ResponseEntity<HistoricalData> getStockDetails(@RequestParam("fromDate")String fromDate,@RequestParam("toDate")String toDate,
			@RequestParam("instrumentId")String instrumentId,@RequestParam("interval")String interval){
		logger.info("inside controller for historicalData " + instrumentId);
		HistoricalData data = null;
		try {
			data = service.getHistoricalDataAsPerDate(util.formatDate(fromDate), util.formatDate(toDate), instrumentId, interval);
		} catch (ParseException e) {
			logger.error("Error while parsing date", e);
			throw new StockDetailsException(e.getMessage(), e);
		} 
		
		if(data==null)
			throw new StockDetailsNotFoundException("No details found");
		return ResponseEntity.ok(data);
	}

}
