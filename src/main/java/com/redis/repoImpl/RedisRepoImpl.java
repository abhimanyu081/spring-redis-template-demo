package com.redis.repoImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.constants.RedisKeyConstants;
import com.redis.model.Company;
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

	@Override
	public void putAll(List<Company> data) {
		
		Map<String,Company> tempMap = new HashMap<>();
		for(Company obj :data) {
			String key =Company.class.getSimpleName()+":"+obj.getSymbol();
			tempMap.put(key, obj);
			redisTemplate.opsForHash().putAll(key, convertToMap(obj));
		}
		
		
		
	}
	
	public Map<String,Object> convertToMap(Object object){
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String,Object> map=mapper.convertValue(object, Map.class);
		return map;
		
	}
	
	public Set<String> getKeys(String pattern){
		return redisTemplate.keys("Company*");
	}
	
	@Override
	public List<Object> getCompaniesBySymbols(List<String> symbols){
		Set<String> keys = new HashSet<>(symbols.size());
		for(String symbol:symbols) {
			String key = Company.class.getSimpleName()+":"+symbol;
			keys.add(key);
		}
		return redisTemplate.opsForValue().multiGet(keys);
		
	}
	
	
}
