package com.redis.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redis.repo.RedisRepo;

@RestController
public class RedisDataViewer {
	
	@Autowired RedisRepo redisRepo;
	
	
	@RequestMapping("company/all")
	public List<Object> getCompaniesBySymbols(@RequestParam("symbols")String symbols){
		List<String> symbolList = Arrays.asList(symbols.split(","));
		return redisRepo.getCompaniesBySymbols(symbolList);
	}

}
