package com.salmon.ssata.backend.dao.profile.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.salmon.ssata.backend.dao.GenericDAOImpl;
import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.dao.profile.service.CompanyProfileDAO;
import com.salmon.ssata.backend.dto.profile.CompanyProfileDTO;
import com.salmon.ssata.common.ErrorMessages;

/**
 * @author mdani
 *
 */

@Repository
public class CompanyProfileDAOImpl extends GenericDAOImpl implements CompanyProfileDAO{

	@Autowired
	public CompanyProfileDAOImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public void createProfile(CompanyProfileDTO profileDTO) throws DaoProfileException{
		
		try{
			
			if(profileDTO != null){
				
				super.create(profileDTO);

			}else{
				throw new DaoProfileException(ErrorMessages.DAO_COMP_PROFILE_CANT_PERSIST);
			}
			
		}catch(DaoProfileException dpe){
			throw dpe;
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoProfileException(ErrorMessages.DAO_COMP_PROFILE_CANT_CREATE);
		}
		
	}

	@Override
	public void updateProfile(CompanyProfileDTO profileDTO) throws DaoProfileException{
		
		try{
			
			if(profileDTO != null){
				
				super.update(profileDTO);

			}else{
				throw new DaoProfileException(ErrorMessages.DAO_COMP_PROFILE_CANT_PERSIST);
			}
			
		}catch(DaoProfileException dpe){
			throw dpe;
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoProfileException(ErrorMessages.DAO_COMP_PROFILE_CANT_CREATE);
		}
		
	}
	
	@Override
	public CompanyProfileDTO getByInvoiceCode(String code){

		CompanyProfileDTO profileDTO = null;
		
			@SuppressWarnings("unchecked")
			List<CompanyProfileDTO> profileDTOList = 
				(List<CompanyProfileDTO>) this.getHibernateTemplate()
						.find("from CompanyProfileDTO as profile where profile.invoiceCode = ?", code);
		
			if (profileDTOList.size() > 0) {
				profileDTO = profileDTOList.get(0);
				return profileDTO;
			}
		return null;
	}
	
	@Override
	public List<CompanyProfileDTO> getAll() throws DaoProfileException{
		// TODO Auto-generated method stub
		
		try{

			@SuppressWarnings("unchecked")
			List<CompanyProfileDTO> profileDTOList =
					(List<CompanyProfileDTO>) this.getHibernateTemplate()
												.find("from CompanyProfileDTO");
		
			if (profileDTOList.size() > 0) {
				return profileDTOList;
			}else{
				throw new DaoProfileException(ErrorMessages.DAO_COMP_PROFILE_USEROBJ_NOT_FOUND);
			}
				
		}catch(DaoProfileException dpe){
			throw dpe;
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoProfileException(ErrorMessages.DAO_COMP_PROFILE_ERROR_USEROBJ);
		}
	}

	@Override
	public CompanyProfileDTO getById(String id)
			throws DaoProfileException {
		try{

			@SuppressWarnings("unchecked")
			List<CompanyProfileDTO> companyDTOList =
					(List<CompanyProfileDTO>) this.getHibernateTemplate()
												.find("from CompanyProfileDTO company where company.id =? ", id);
		
			if (companyDTOList.size() > 0) {
				return companyDTOList.get(0);
			}else{
				throw new DaoProfileException(ErrorMessages.DAO_COMP_PROFILE_ERROR_ID);
			}
				
		}catch(DaoProfileException dpe){
			throw dpe;
		}catch(Exception e){
			e.printStackTrace();
			throw new DaoProfileException(ErrorMessages.DAO_COMP_PROFILE_ERROR_ID);
		}

	}	
	
	
}
