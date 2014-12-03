package com.salmon.ssata.backend.dao.apptitude.service;

import java.util.List;

import com.salmon.ssata.backend.dto.TestTypeDTO;

public interface TestTypeDAO {

	public void createTestType(TestTypeDTO testTypeDTO) throws TestTypeDAOException;
	public List<TestTypeDTO> getAllTestType() throws TestTypeDAOException;
	
}
