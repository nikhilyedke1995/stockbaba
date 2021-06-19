package com.stockbaba.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stockbaba.example.entity.StockInfo;
import com.stockbaba.example.repository.StockInfoRepository;

@RestController
@RequestMapping("/chart")
public class StockChartControlle {

	@Autowired
	private StockInfoRepository repo;
	
	@GetMapping("/stock/")
	public List<StockInfo> getStockChart(@RequestParam("stockName")String stockName){
		System.out.println("Inside controller for stock: " + stockName);
		List<StockInfo> list = repo.findByNameContaining(stockName);
		System.out.println("list.size" + list.size());
		return list;
	}
}
