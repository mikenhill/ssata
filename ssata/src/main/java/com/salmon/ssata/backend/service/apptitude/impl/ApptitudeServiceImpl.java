package com.salmon.ssata.backend.service.apptitude.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.salmon.ssata.backend.dto.TestTypeDTO;
import com.salmon.ssata.backend.service.apptitude.service.ApptitudeService;
import com.salmon.ssata.backend.service.profile.ServiceProfileException;

public class ApptitudeServiceImpl implements ApptitudeService {

	@Autowired
	private com.salmon.ssata.backend.dao.apptitude.impl.TestTypeDAOImpl testTypeDAO;
	
	@Override
	public List<TestTypeDTO> getAllTestTypes() throws ServiceProfileException {
		List<TestTypeDTO> testTypeDTOs = null;
		try {
			testTypeDTOs = testTypeDAO.getAllTestType();
		} catch (Exception ex) {
			
		}
		return testTypeDTOs;
	}

}
