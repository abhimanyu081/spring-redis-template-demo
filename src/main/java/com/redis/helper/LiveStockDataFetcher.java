package com.redis.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.redis.model.MutualFundDto;

@Component
public class LiveStockDataFetcher {

	public static final String DATA_LINK = "http://192.168.25.136/ET_Market/marketdata";
	public static final String MF_DATA_LINK = "http://qc.bselivefeeds.indiatimes.com/mf1/schemes/all.htm?size=10";

	public String fetchLiveStockDataFromUrl(String dataType) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("datatype", dataType);
		return restTemplate.getForEntity(DATA_LINK, String.class, params).getBody();
	}

	public List<MutualFundDto> getMutualFundData() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
        //Add the Jackson Message converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
// Note: here we are making this converter to process any kind of response, 
// not only application/*json, which is the default behaviour
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));         
		messageConverters.add(converter);  
		restTemplate.setMessageConverters(messageConverters);  
		
		MutualFundDto[] mfs=restTemplate.getForEntity(MF_DATA_LINK, MutualFundDto[].class).getBody();
		return Arrays.asList(mfs);
		
	}

}
