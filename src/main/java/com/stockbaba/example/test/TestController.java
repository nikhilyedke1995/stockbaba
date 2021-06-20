package com.stockbaba.example.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.HistoricalData;
import com.zerodhatech.models.Instrument;
import com.zerodhatech.models.User;

@RestController
@RequestMapping("/test")
public class TestController {
	
	KiteConnect kiteSdk;
	
	boolean isLogin = false;

	@GetMapping("/stock")
	public String getStockDetailsYahooTest(@RequestParam(value = "stockName")String stockName) throws IOException, InterruptedException, JSONException, KiteException, ParseException {

		if(!isLogin)
			getKiteSession();
		return new ObjectMapper().writeValueAsString(kiteSdk.getInstruments().parallelStream().map(Instrument::getName).collect(Collectors.toList()));
//		List<Instrument> list = kiteSdk.getInstruments();
//		list.forEach(System.out::println);
//		Optional<Instrument> optionalInstrument = list.parallelStream().filter(k->{return stockName.equalsIgnoreCase(k.getName());}).findFirst();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		if(optionalInstrument.isPresent()) {
//			HistoricalData data = kiteSdk.getHistoricalData(format.parse("2021-06-01"), format.parse("2021-06-18"),String.valueOf(optionalInstrument.get().exchange_token),"60",true,false);
//			return new ObjectMapper().writeValueAsString(data);
//		}else {
//			return stockName + "Not present";
//		}
		
	}
	
	private void getKiteSession() throws JSONException, IOException, KiteException {
		kiteSdk = new KiteConnect("gogn");
		kiteSdk.setUserId("gaga");
		User user = kiteSdk.generateSession("agaga", "agag");
		kiteSdk.setAccessToken(user.accessToken);
		kiteSdk.setPublicToken(user.publicToken);
		isLogin = true;
		System.out.println(kiteSdk.getLoginURL());
		kiteSdk.setSessionExpiryHook(()->{
			System.out.println("Session expired");
			System.out.print(kiteSdk.getLoginURL());
		});
	}
	
	
}
