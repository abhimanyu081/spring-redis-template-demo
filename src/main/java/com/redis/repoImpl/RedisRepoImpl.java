package com.redis.repoImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Repository;

import com.redis.constants.RedisKeyConstants;
import com.redis.model.MutualFundDto;
import com.redis.repo.RedisRepo;

@Repository
public class RedisRepoImpl implements RedisRepo{
	
	@Autowired RedisTemplate<String,Object> redisTemplate;

	@Override
	public void batchInsert(List<MutualFundDto> mfData) {
		System.out.println("size = "+mfData.size());
		Set<TypedTuple<Object>> mfDataSet = new HashSet<>();
		
		for(MutualFundDto mf : mfData) {
			TypedTuple<Object> tuple = new DefaultTypedTuple<Object>(mf, mf.getLatestNav());
			mfDataSet.add(tuple);
		}
		System.out.println("size = "+mfDataSet.size());
		redisTemplate.opsForZSet().add(RedisKeyConstants.KEY_ET_MF_DATA, mfDataSet);
	}
}
