package com.stockbaba.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.zerodhatech.kiteconnect.KiteConnect;

@Configuration
@PropertySources({@PropertySource("classpath:kite.properties")})
public class KiteConfiguration {

	
	
	
	
	
	@Value("${kite.apiKey}")
	private String apiKey;
	
	@Value("${kite.userId}")
	private String userId;
	
	@Value("${kite.requestToken}")
	private String requestToken;
	
	@Value("${kite.apiSecret}")
	private String apiSecret;
	
	@Bean
	public KiteConnect getKiteConnect() {
		
	}
	
}
