package com.salmon.ssata.backend.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salmon.ssata.backend.dao.apptitude.service.TestTypeDAO;
import com.salmon.ssata.backend.dto.TestTypeDTO;
import com.salmon.ssata.backend.service.ServiceException;
import com.salmon.ssata.common.ErrorMessages;
import com.salmon.ssata.web.form.TestTypeForm;

@Service("TestServiceImpl")
public class TestServiceImpl implements TestService {

	@Autowired
	private TestTypeDAO testTypeDAO; 
	
	@Override
	public boolean addTestType(TestTypeForm form) throws ServiceException {
		
		try{
			
			TestTypeDTO testTypeDTO = new TestTypeDTO();
			testTypeDTO.setType(form.getTestType());
			testTypeDTO.setDescription(form.getDescription());
			
			this.testTypeDAO.createTestType(testTypeDTO);
						
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ErrorMessages.DAO_UNABLE_TO_CREATE_TESTTYPE.getMsgCode());
			
		}
		
		return false;
		
	}

	
	
}
