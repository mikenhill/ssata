package com.salmon.ssata.backend.service.common;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salmon.ssata.backend.service.ServiceException;
import com.salmon.ssata.backend.service.test.TestService;
import com.salmon.ssata.web.form.TestTypeForm;



@Service
public class ConfigDataComposerServiceImpl  {

	public Map<String, String> testTypeData;
	
	@Autowired
	private TestService testService;
	
	public final void parseAndPrepareConfigData() throws ServiceException {

		ConfigDataSet dataSet = new ConfigDataSet();

		// Set promoters
		this.testTypeData = dataSet.setTestType();
		initWithTestTypeData();

	}
	
	public void initWithTestTypeData() throws ServiceException {
		TestTypeForm form = new TestTypeForm();
		
		for (Map.Entry<String, String> entry : this.testTypeData.entrySet()) {
			form.setTestType(entry.getKey());
			form.setDescription(entry.getValue());
			testService.addTestType(form);
		}
		
		
	}

}
