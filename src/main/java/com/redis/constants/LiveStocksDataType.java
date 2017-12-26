package com.redis.constants;

public enum LiveStocksDataType {
	
	SENSEX("sensex"),NIFTY("nifty"),SENSEX_INC("sensex_inc"),NIFTY_INC("nse_inc");
	
	private String dataType;
	
	
	
	private LiveStocksDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataType(){
		return dataType;
	}

	

}
