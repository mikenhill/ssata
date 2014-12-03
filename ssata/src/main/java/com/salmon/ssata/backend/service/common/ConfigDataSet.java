package com.salmon.ssata.backend.service.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ConfigDataSet {

private boolean executeOnce;
	
	public boolean isExecuteOnce() {
		return executeOnce;
	}

	public void setExecuteOnce(boolean executeOnce) {
		this.executeOnce = executeOnce;
	}

	public ConfigDataSet() {
		// TODO Auto-generated constructor stub
	}

	
	public Map<String, String> setTestType(){
		
		//Java
		Map<String, String> testTypeMap = new HashMap <String, String>();
		testTypeMap.put("Java", "The JEE Java test");
		
		return testTypeMap;
		
	}
	
}
