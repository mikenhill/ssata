package com.salmon.ssata.backend.service.test;

import org.springframework.stereotype.Service;

import com.salmon.ssata.backend.service.ServiceException;
import com.salmon.ssata.web.form.TestTypeForm;


public interface TestService {

	public boolean addTestType(TestTypeForm form) throws ServiceException ;
	
}
