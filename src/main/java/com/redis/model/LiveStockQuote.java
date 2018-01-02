package com.redis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LiveStockQuote implements  Hashable<LiveStockQuote>{
	
	@JsonProperty(value="1. symbol")
	private String symbol;
	
	@JsonProperty(value="2. price")
	private String price;
	
	
	@JsonProperty(value="3. volume")
	private String volume;
	
	@JsonProperty(value="4. timestamp")
	private String dateTime;
	
	
	
	
	

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	
	@Override
	public String getId() {
		return symbol;
	}

	@Override
	public String toString() {
		return "LiveStockQuote [symbol=" + symbol + ", price=" + price + ", volume=" + volume + ", dateTime=" + dateTime
				+ "]";
	}
	
	

	
}
