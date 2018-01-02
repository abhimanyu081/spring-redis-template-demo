package com.redis.helper;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.config.ApplicationProperties;
import com.redis.model.LiveStockQuote;

@Component
public class LiveStockDataFetcher {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	ApplicationProperties props;
	
	public static enum API_FUNCTION{
		BATCH_STOCK_QUOTES;
		
	}

	
	public static final String DATA_LINK = "http://192.168.25.136/ET_Market/marketdata";
	public static final String MF_DATA_LINK = "http://qc.bselivefeeds.indiatimes.com/mf1/schemes/all.htm?size=10";

	public static final String BATCH_QUOTE_LINK = "https://www.alphavantage.co/query?function=BATCH_STOCK_QUOTES&apikey=88O9B6TBK5RV5ACH&";
	
	public String fetchLiveStockDataFromUrl(String dataType) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("datatype", dataType);
		return restTemplate.getForEntity(DATA_LINK, String.class, params).getBody();
	}

	
	public  List<LiveStockQuote> getBatchStockQuote(String symbols){
		try {
			String jsonStrResponse=getResponseString(BATCH_QUOTE_LINK, 
					getParamsMap(symbols, API_FUNCTION.BATCH_STOCK_QUOTES.name(), props.getStockDataApiKey()));
			if(jsonStrResponse!=null) {
				JSONObject jsn = new JSONObject(jsonStrResponse);
				if(jsn.has("Stock Quotes")) {
					JSONArray stockJsonArr = jsn.getJSONArray("Stock Quotes");
					ObjectMapper mapper = new ObjectMapper();
					LiveStockQuote[] stockQuotes=mapper.readValue(stockJsonArr.toString(), LiveStockQuote[].class);
					
					if(stockQuotes!=null) {
						LOG.debug("{} stock quotes found ",stockQuotes.length);
						return Arrays.asList(stockQuotes);
					}
					LOG.debug("No stock quotes found ");
				}
			}
		} catch (JsonParseException e) {
			LOG.error("JSON Parsed Exception", e.getOriginalMessage());
		} catch (JsonMappingException e) {
			e.printStackTrace();
			
			LOG.error("Exception Occured While Mapping json to POJO", e.getOriginalMessage());
		} catch (JSONException e) {
			LOG.error("JSON Parsed Exception", e.getMessage());
		} catch (IOException e) {
			LOG.error("IO Exception");
		}
		return null;
		
	}
	
	public String getResponseString(String url, Map<String,String> params) {
		System.out.println(params);
		RestTemplate template = new RestTemplate();
		url = BATCH_QUOTE_LINK+"&symbols="+params.get("symbols");
		System.out.println(url);
		String str =template.getForEntity(url,String.class).getBody();
		System.out.println("############   URRL RESPONSE ###########");
		
		System.out.println(str);
		return str;
		
	}
	
	public Map<String,String> getParamsMap(String symbols,String function,String apiKey){
		Map<String,String> params = new HashMap<>();
		params.put("function", API_FUNCTION.BATCH_STOCK_QUOTES.name());
		params.put("symbols", symbols);
		params.put("apikey", props.getStockDataApiKey());
		return params;
		
	}

}
