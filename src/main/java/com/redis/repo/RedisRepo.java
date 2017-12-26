package com.redis.repo;

import java.util.List;

import com.redis.model.LiveStockQuote;

public interface RedisRepo {
	
	public void batchInsert(List<LiveStockQuote> stockQuotes);

}
