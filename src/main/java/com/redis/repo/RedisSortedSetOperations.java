package com.redis.repo;

import java.util.List;
import java.util.Set;

import com.redis.model.Tuple;

public interface RedisSortedSetOperations<T> {
	
	public void insertAllToSortedSet(String key, List<? extends Tuple<T>> objects);

	public Set<T> findAllFromSortedSet(String key);

	public Set<T> findAllFromSortedSet(String key, int start, int limit);
	
}
