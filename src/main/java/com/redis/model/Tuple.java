package com.redis.model;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

public interface Tuple<V> {

	public TypedTuple<V> createTuple();
}
