package com.redis.repo;

import java.util.List;

import com.redis.model.Hashable;

public interface RedisHashOps<T> {
	
	
	
	public void insertAll(List<? extends Hashable<T>> objects, String keyPrefix);
	List<T> fetchAll(String pattern, Class<T> type);
	
}
