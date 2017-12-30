package com.redis.repoImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Repository;

import com.redis.model.Tuple;
import com.redis.repo.RedisSortedSetOperations;

@Repository
public class RedisSortedSetOperationsImpl<T> implements RedisSortedSetOperations<T> {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass().getSimpleName());

	private final int START_INDEX = 0;
	private final int END_INDEX = -1;

	@Autowired
	RedisTemplate<String, T> redisTemplate;

	@SuppressWarnings("unchecked")
	private Set<TypedTuple<T>> createTypedTuple(List<? extends Tuple<T>> objects) {
		Set<TypedTuple<T>> typedSet = new HashSet<>();

		if(objects!=null&&objects.size()>0) {
			for (Tuple<T> obj : objects) {
				TypedTuple<T> tuple = obj.createTuple();
				typedSet.add(tuple);
			}
		}
		
		return typedSet;
	}

	@Override
	public void insertAllToSortedSet(String key,List<? extends Tuple<T>> objects) {
		
		if(objects!=null&&objects.size()>0) {
			Set<TypedTuple<T>> typedSet = createTypedTuple(objects);
			if(typedSet!=null&&typedSet.size()>0) {
				redisTemplate.opsForZSet().add(key, typedSet);
			}
		}
	}

	
	public Set<String> getKeys(String pattern) {
		return redisTemplate.keys(pattern);
	}


	@Override
	public Set<T> findAllFromSortedSet(String key) {
		return redisTemplate.opsForZSet().range(key, START_INDEX, END_INDEX);

	}
	
	@Override
	public Set<T> findAllFromSortedSet(String key,int start, int limit) {
		return redisTemplate.opsForZSet().range(key, start, limit);

	}

}
