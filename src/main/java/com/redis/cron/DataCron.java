package com.redis.cron;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.redis.constants.CacheUtil;
import com.redis.constants.RedisKeyConstants;
import com.redis.helper.CsvFileParser;
import com.redis.helper.LiveStockDataFetcher;
import com.redis.model.Company;
import com.redis.model.LiveStockQuote;
import com.redis.repo.RedisHashOps;
import com.redis.repo.RedisSortedSetOperations;

@Component
public class DataCron {

	@Autowired RedisSortedSetOperations<LiveStockQuote> redisRepo;
	
	@Autowired LiveStockDataFetcher dataFetcher;
	
	@Autowired CsvFileParser csvParser;
	
	@Autowired RedisHashOps< LiveStockQuote> hashOps;
	
	@Autowired RedisTemplate<String, LiveStockQuote> redisTemplate;
	
	
	
	@PostConstruct
	public void init() {
		
		StringBuilder symbols =new StringBuilder(); 
		int i=0;
		for(String symbol :redisTemplate.keys("Company*")) {
			if(i++==50) {
				break;
			}
			symbol=symbol.replace("Company:", "");
			symbols.append(symbol);
			symbols.append(",");
		}
		System.out.println(symbols+"******KEYS *********************************");
		hashOps.insertAll(dataFetcher.getBatchStockQuote(symbols.toString()), RedisKeyConstants.KEY_ET_LIVE_NYSE_QUOTES);
		
		try {
			loadCompanyMeta();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	
	
	public void loadCompanyMeta() throws IOException {
		List<Company> companies=csvParser.parseCompanyCSV();
		if(companies!=null&&companies.size()>0) {
			for(Company comapny:companies) {
				if(comapny!=null&&comapny.getSymbol()!=null) {
					CacheUtil.COPANY_META_MAP.put(comapny.getSymbol(), comapny);
				}
			}
		}
	}
	
}