package com.redis.model;

import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencsv.bean.CsvBindByName;

public class Company implements Tuple<Company>{

	@CsvBindByName
	private String name;

	@CsvBindByName
	private String symbol;

	@CsvBindByName
	private Double marketCap;

	@CsvBindByName
	private String sector;

	@CsvBindByName
	private String industry;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	

	public Double getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(Double marketCap) {
		this.marketCap = marketCap;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + ", symbol=" + symbol + ", marketCap=" + marketCap + ", sector=" + sector
				+ ", industry=" + industry + "]";
	}

	@Override
	@JsonIgnore
	public TypedTuple<Company> createTuple() {
		TypedTuple<Company> tuple = new DefaultTypedTuple<Company>(this, this.getMarketCap());
		return tuple;
	}

}
