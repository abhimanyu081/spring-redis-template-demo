package com.redis.cron;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.redis.helper.CsvFileParser;
import com.redis.helper.LiveStockDataFetcher;
import com.redis.model.Company;
import com.redis.repo.RedisRepo;

@Component
public class DataCron {

	@Autowired RedisRepo redisRepo;
	
	@Autowired LiveStockDataFetcher dataFetcher;
	
	@Autowired CsvFileParser csvParser;
	
	
	
	@PostConstruct
	public void init() {
		try {
			List<Company> companyMetaData=csvParser.parseCompanyCSV();
			redisRepo.putAll(companyMetaData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
