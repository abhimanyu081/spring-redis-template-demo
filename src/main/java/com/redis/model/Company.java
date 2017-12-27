package com.redis.model;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;

public class Company {

	@CsvBindByName
	private String name;

	@CsvBindByName
	private String symbol;

	@CsvBindByName
	private BigDecimal marketCap;

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

	public BigDecimal getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(BigDecimal marketCap) {
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

}
