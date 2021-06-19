package com.stockbaba.example.processor;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.json.JSONException;
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
	
	@PostConstruct
	public void saveDataToStockInfoDB() {
		Function<Instrument,StockInfo> func = (t->{
			StockInfo stockInfo = new StockInfo(t.getTradingsymbol(),t.getName(),
					t.getInstrument_token(),t.getExchange_token(),t.getExchange());
			return stockInfo;
		});
		List<StockInfo> list = null;
		try {
			System.out.println("getting data from zerodha");
			list = connect.getInstruments().parallelStream().map(func).collect(Collectors.toList());
			System.out.println("data retrieval done!!");
		} catch (JSONException | IOException | KiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list!=null)
		stockInfoRepo.saveAll(list);
	}
	
}
