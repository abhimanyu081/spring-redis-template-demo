package com.redis.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LievStockDataFetcher {
	
	public static final String DATA_LINK = "http://192.168.25.136/ET_Market/marketdata?datatype=";
	
	public String fetchLiveStockDataFromUrl(String dataType){
		RestTemplate restTemplate = new RestTemplate();
		
	}

}
