package com.redis.repoImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.redis.model.Hashable;
import com.redis.repo.RedisHashOps;
import com.redis.util.DeserializeUtil;

@Repository
public class RedisHashOpsImpl<T> implements RedisHashOps<T> {
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public List<T> fetchAll(String pattern, Class<T> type) {
		List<T> list = new ArrayList<T>();
		Set<String> ids = redisTemplate.keys(pattern);
		redisTemplate.executePipelined(new RedisCallback<List<Object>>() {
			
			@Override
			public List<Object> doInRedis(RedisConnection conn) throws DataAccessException {
				
				for(String key : ids) {
					Map<byte[],byte[]> hash=conn.hGetAll(key.getBytes());
					T t=DeserializeUtil.deserializeByteArrayMapToPojo(hash, type);
					list.add(t);
				}
				return null;
			}
		});
		return list;
		
		
	}


	


	
	@Override
	public void insertAll(List<? extends Hashable<T>> objects, String keyPrefix) {
		redisTemplate.executePipelined(new RedisCallback<List<Object>>() {

			@Override
			public List<Object> doInRedis(RedisConnection conn) {
				for (Hashable<T> t : objects) {
					System.out.println(t );
					String id = t.getId();
					String prefix = StringUtils.isNotBlank(keyPrefix) ? keyPrefix : t.getClass().getSimpleName();
					Map<byte[], byte[]> byteMap = getByteArrMapFromObject(t);
					String key = prefix + ":" + id;
					conn.hMSet(key.getBytes(), byteMap);
					
				}
				return null;
			}
		});

	}

	
	public Map<byte[], byte[]> getByteArrMapFromObject(Object o) {
		Map<String, String> objMap;
		try {

			objMap = BeanUtils.describe(o);
			Map<byte[], byte[]> byteMap = new HashMap<>();
			for (Map.Entry<String, String> entry : objMap.entrySet()) {
				byteMap.put(entry.getKey().getBytes(), entry.getValue().getBytes());

			}
			return byteMap;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public Map<String,String> getMapFromObject(Object o){
		try {
			Map<String,String> map=BeanUtils.describe(o);
			return map;
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}
