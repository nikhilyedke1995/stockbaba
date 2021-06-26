package com.stockbaba.example.service;

import java.io.IOException;
import java.util.Date;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockbaba.example.exception.StockDetailsException;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.HistoricalData;

@Service
public class StockDetailsService {

	@Autowired
	private KiteConnect connect;
	
	private static final Logger logger = LoggerFactory.getLogger(StockDetailsService.class);
	
	public HistoricalData getHistoricalDataAsPerDate(Date fromDate, Date toDate, String instrumentId,String interval) {
		HistoricalData data = null;
		try {
			data = connect.getHistoricalData(fromDate,toDate, instrumentId, interval, false, false);
		} catch (JSONException | IOException | KiteException e) {
			logger.error("Error while retrieving data ", e);
			throw new StockDetailsException(e.getMessage(), e);
		} 
		return data;
	}
	
}
