package com.redis.repo;

import java.util.List;

import com.redis.model.MutualFundDto;

public interface RedisRepo {
	
	public void batchInsert(List<MutualFundDto> mfData);

}
