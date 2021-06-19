package com.stockbaba.example.processor;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.stockbaba.example.entity.StockInfo;
import com.stockbaba.example.repository.StockInfoRepository;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.Instrument;

@Configuration
public class DatabaseFeeder {

	@Autowired
	private KiteConnect connect;
	
	@Autowired
	private StockInfoRepository stockInfoRepo;
	
	private static Logger logger = LoggerFactory.getLogger(DatabaseFeeder.class);
	
	@PostConstruct
	public void saveDataToStockInfoDB() {
		Function<Instrument,StockInfo> func = (t->{
			StockInfo stockInfo = new StockInfo(t.getTradingsymbol(),t.getName(),
					t.getInstrument_token(),t.getExchange_token(),t.getExchange());
			return stockInfo;
		});
		List<StockInfo> list = null;
		try {
			logger.info("starting data retrieval");
			list = connect.getInstruments().parallelStream().map(func).collect(Collectors.toList());
			logger.info("data retrieval done!!");
		} catch (JSONException | IOException | KiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list!=null)
		stockInfoRepo.saveAll(list);
	}
	
}
