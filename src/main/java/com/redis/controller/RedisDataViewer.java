package com.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redis.model.Company;
import com.redis.model.LiveStockQuote;
import com.redis.repo.RedisHashOps;
import com.redis.repo.RedisSortedSetOperations;

@RestController
public class RedisDataViewer {
	
	@Autowired RedisSortedSetOperations<Company> redisRepo;
	
	@Autowired RedisHashOps<LiveStockQuote> hashOps;
	
	@RequestMapping("/all")
	public List<LiveStockQuote> getaLlQuotes(){
		String pattern = "Live_Stock_NYSE*";
		return hashOps.fetchAll(pattern, LiveStockQuote.class);
	}
	

}
