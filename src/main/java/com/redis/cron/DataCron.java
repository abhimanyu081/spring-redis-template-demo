package com.redis.cron;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.redis.constants.RedisKeyConstants;
import com.redis.helper.CsvFileParser;
import com.redis.helper.LiveStockDataFetcher;
import com.redis.model.LiveStockQuote;
import com.redis.repo.RedisSortedSetOperations;

@Component
public class DataCron {

	@Autowired RedisSortedSetOperations<LiveStockQuote> redisRepo;
	
	@Autowired LiveStockDataFetcher dataFetcher;
	
	@Autowired CsvFileParser csvParser;
	
	
	
	@PostConstruct
	public void init() {
		List<LiveStockQuote> companyMetaData=new ArrayList<>();
		redisRepo.insertAllToSortedSet(RedisKeyConstants.EQUITY_GAINERS, companyMetaData);
	}
	
}