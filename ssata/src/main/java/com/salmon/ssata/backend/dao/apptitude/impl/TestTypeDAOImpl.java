package com.salmon.ssata.backend.dao.apptitude.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.salmon.ssata.backend.dao.GenericDAOImpl;
import com.salmon.ssata.backend.dao.apptitude.service.TestTypeDAO;
import com.salmon.ssata.backend.dao.apptitude.service.TestTypeDAOException;
import com.salmon.ssata.backend.dto.TestTypeDTO;
import com.salmon.ssata.common.ErrorMessages;

@Repository
public class TestTypeDAOImpl  extends GenericDAOImpl implements TestTypeDAO {

	@Autowired
	public TestTypeDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	

	
	@Override
	public List<TestTypeDTO> getAllTestType() throws TestTypeDAOException {
		
		List<TestTypeDTO> allTestTypeDTO = new ArrayList<TestTypeDTO>();
		
		try{

			//@SuppressWarnings("unchecked")
			allTestTypeDTO = 
				(List<TestTypeDTO>) this.getHibernateTemplate()
						.find("from TestType as testtype ");
		
			if (allTestTypeDTO.size() > 0) {
				return allTestTypeDTO;
			}else{
				//throw new DaoPromoterException(ErrorMessages.DAO_PRODUCT_CANT_RETRIEVE_ALL);
			}
				
//		}catch(DaoPromoterException dpe){
//			throw dpe;
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return allTestTypeDTO;
		
	}
	
	@Override
	public void createTestType(TestTypeDTO testTypeDTO) throws TestTypeDAOException {
		try{
			
			if(testTypeDTO != null){
				
				super.create(testTypeDTO);

			}else{
				throw new TestTypeDAOException(com.salmon.ssata.common.ErrorMessages.DAO_UNABLE_TO_CREATE_TESTTYPE);
			}
			
		}catch(TestTypeDAOException dpe){
			throw dpe;
		}catch(Exception e){
			e.printStackTrace();
			throw new TestTypeDAOException(ErrorMessages.DAO_UNABLE_TO_CREATE_TESTTYPE);
		}
		
	}

}
