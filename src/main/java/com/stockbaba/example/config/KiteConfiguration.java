package com.stockbaba.example.config;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.User;

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
	
	private KiteConnect connect;
	private boolean isLogged;
	
	@Bean
	public KiteConnect getKiteConnect() {
		if(isLogged)
			return connect;
		
		connect = new KiteConnect(apiKey);
		connect.setUserId(userId);
		try {
			User user = connect.generateSession(requestToken, apiSecret);
			connect.setAccessToken(user.accessToken);
			connect.setPublicToken(user.publicToken);
			isLogged = true;
		} catch (JSONException | IOException | KiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connect;
	}
	
}
