package com.redis.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redis.model.Company;
import com.redis.repo.RedisSortedSetOperations;

@RestController
public class RedisDataViewer {
	
	@Autowired RedisSortedSetOperations<Company> redisRepo;
	
	
	

}
