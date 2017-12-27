package com.redis.cron;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.redis.helper.LiveStockDataFetcher;
import com.redis.repo.RedisRepo;

@Component
public class DataCron {

	@Autowired RedisRepo redisRepo;
	
	@Autowired LiveStockDataFetcher dataFetcher;
	
	@PostConstruct
	public void loadData() {
		System.out.println("Starting cron");
		redisRepo.batchInsert(dataFetcher.getMutualFundData());
	}
}
