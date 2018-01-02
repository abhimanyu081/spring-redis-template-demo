package com.redis.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DeserializeUtil {
	
	public static Map<String,String> deserializeByteArrayMapToStringMap(Map<byte[],byte[]> hash){
		Map<String,String> convertedMap = null;
		if(hash!=null&&hash.size()>0) {
			convertedMap = new HashMap<String,String>(hash.size());
			for(Map.Entry<byte[],byte[]> entry : hash.entrySet()) {
				String keyStr = deserializeByteArray(entry.getKey());
				String value = deserializeByteArray(entry.getValue());
				convertedMap.put(keyStr, value);
			}
		}else {
			convertedMap = new HashMap<String,String>(0);
		}
		return convertedMap;
		
		
	}
	
	@SuppressWarnings("unchecked")
	public static String deserializeByteArray(byte[] bytes) {
		ByteArrayInputStream bais=null;
		ObjectInputStream ois =null;
		String t =null;
		
		try {
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			t = (String) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(ois!=null) ois.close();
			if(bais!=null)bais.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return t;
	}
	
	public static <T> T deserializeByteArrayMapToPojo(Map<byte[],byte[]> hash, Class<T> type){
		Map<String,String> convertedMap = deserializeByteArrayMapToStringMap(hash);
		ObjectMapper mapper = new ObjectMapper();
		T t =mapper.convertValue(convertedMap, type);
		return t;
	}

}
