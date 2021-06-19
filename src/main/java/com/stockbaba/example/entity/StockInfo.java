package com.stockbaba.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stock_info")
public class StockInfo {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String tradingSymbol; 
	
	@Column
	private String name;
	
	@Column
	private long instrument_token;
	
	@Column
	private long exchange_token;
	
	@Column
	private String exchange;
	

	public StockInfo() {
		
	}

	public StockInfo(String tradingSymbol, String name, long instrument_token, long exchange_token,String exchange) {
		super();
		this.tradingSymbol = tradingSymbol;
		this.name = name;
		this.instrument_token = instrument_token;
		this.exchange_token = exchange_token;
		this.exchange = exchange;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTradingSymbol() {
		return tradingSymbol;
	}

	public void setTradingSymbol(String tradingSymbol) {
		this.tradingSymbol = tradingSymbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getInstrument_token() {
		return instrument_token;
	}

	public void setInstrument_token(long instrument_token) {
		this.instrument_token = instrument_token;
	}

	public long getExchange_token() {
		return exchange_token;
	}

	public void setExchange_token(long exchange_token) {
		this.exchange_token = exchange_token;
	}
	
	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	
}
