package com.redis.repo;

import java.util.List;

import com.redis.model.Company;
import com.redis.model.MutualFundDto;

public interface RedisRepo {
	
	public void batchInsert(List<MutualFundDto> mfData);

	public void putAll(List<Company> data);

	List<Object> getCompaniesBySymbols(List<String> symbols);

}
