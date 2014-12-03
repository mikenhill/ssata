package com.salmon.ssata.backend.service.profile.impl;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.dao.profile.service.CompanyProfileDAO;
import com.salmon.ssata.backend.dao.profile.service.PersonalProfileDAO;
import com.salmon.ssata.backend.dto.profile.CompanyProfileDTO;
import com.salmon.ssata.backend.dto.profile.PersonalProfileDTO;
import com.salmon.ssata.backend.service.GenericServiceImpl;
import com.salmon.ssata.backend.service.profile.ServiceProfileException;
import com.salmon.ssata.backend.service.profile.service.CompanyProfileService;
import com.salmon.ssata.backend.service.profile.service.PersonalProfileService;
import com.salmon.ssata.common.ErrorMessages;
import com.salmon.ssata.web.forms.profile.RegistrationForm;
import com.salmon.ssata.web.models.profile.CompanyModel;
import com.salmon.ssata.web.models.profile.ProfileModel;

/**
 * @author mdani
 *
 */

@Service("CompanyProfileServiceImpl")
public class CompanyProfileServiceImpl extends GenericServiceImpl implements CompanyProfileService{

	private static final long serialVersionUID = 1L;

	@Autowired
	private CompanyProfileDAO companyProfileDAOImpl;
	
	@Autowired
	private PersonalProfileService personalProfileService; 
	
	@Autowired
	private PersonalProfileDAO personalProfileDAO; 
	
	
	
	@Override
	public ProfileModel createProfile(RegistrationForm form, String personalProfileId) 
									throws DaoProfileException, ServiceProfileException {
		// TODO Auto-generated method stub
		ProfileModel profileModel = null;
		
		try {
			
			CompanyProfileDTO companyProfileDTO = new CompanyProfileDTO();
			
			companyProfileDTO.setCompanyName(form.getCompanyName());
//			companyProfileDTO.setCompanyEmail(form.getCompanyEmail());
//			companyProfileDTO.setAddressLine1(form.getAddressLine1());
//			companyProfileDTO.setAddressLine2(form.getAddressLine2());
//			companyProfileDTO.setAddressLine3(form.getAddressLine3());
//			companyProfileDTO.setCity(form.getCity());
//			companyProfileDTO.setCountry(form.getCountry());
//			companyProfileDTO.setPostcode(form.getPostcode());
			
			companyProfileDAOImpl.createProfile(companyProfileDTO);
			
			companyProfileDTO.setInvoiceCode(this.generateUniqueCode());
			companyProfileDAOImpl.updateProfile(companyProfileDTO);
			
			PersonalProfileDTO personalProfileDTO = personalProfileDAO.getById(personalProfileId);
			personalProfileDTO.setCompany(companyProfileDTO);
			personalProfileDAO.updateProfile(personalProfileDTO);
			
			profileModel = new ProfileModel();
			profileModel.setCompanyName(companyProfileDTO.getCompanyName());

		} catch (DaoProfileException dpe) {
			throw dpe;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_COMP_PROFILE_CANT_CREATE);
		}
		return profileModel;
	}

	@Override
	public ProfileModel updateProfile(RegistrationForm form, String personalProfileId) 
									throws DaoProfileException, ServiceProfileException {
		ProfileModel profileModel = null;
		
		try {
			
			CompanyProfileDTO companyProfileDTO = personalProfileDAO.getById(personalProfileId).getCompany();
			
			companyProfileDTO.setCompanyName(form.getCompanyName());
//			companyProfileDTO.setCompanyEmail(form.getCompanyEmail());
//			companyProfileDTO.setAddressLine1(form.getAddressLine1());
//			companyProfileDTO.setAddressLine2(form.getAddressLine2());
//			companyProfileDTO.setAddressLine3(form.getAddressLine3());
//			companyProfileDTO.setCity(form.getCity());
//			companyProfileDTO.setCountry(form.getCountry());
//			companyProfileDTO.setPostcode(form.getPostcode());
			
			companyProfileDAOImpl.updateProfile(companyProfileDTO);
			profileModel = new ProfileModel();
			profileModel.setCompanyName(companyProfileDTO.getCompanyName());

			
		} catch (DaoProfileException dpe) {
			throw dpe;
		}
		catch (Exception e){
			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_COMP_PROFILE_CANT_CREATE);
		}
		
		return profileModel;
	}

	
	
	
	@Override
	public ProfileModel getProfileByUser(String personalProfileId) throws DaoProfileException, ServiceProfileException {
		
		ProfileModel profileModel = null;
		try {

			PersonalProfileDTO personalProfileDTO = personalProfileDAO.getById(personalProfileId);
			
			profileModel = new ProfileModel();
			profileModel.setCompanyName(personalProfileDTO.getCompany().getCompanyName());
			
		} catch (DaoProfileException dpe) {
			throw dpe;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_PERSONAL_PROFILE_CANT_CREATE);
		}
		return profileModel;
	}	

	@Override
	public CompanyModel getCompanyByProfileId(String personalProfileId)
			throws DaoProfileException, ServiceProfileException {
		
		CompanyModel companyModel =null;
		
		try {

			PersonalProfileDTO personalProfileDTO = personalProfileDAO.getById(personalProfileId);
			
			companyModel = new CompanyModel();
			companyModel.setCompanyName(personalProfileDTO.getCompany().getCompanyName());
			companyModel.setAddressLine1(personalProfileDTO.getCompany().getAddressLine1());
			companyModel.setAddressLine2(personalProfileDTO.getCompany().getAddressLine2());
			companyModel.setAddressLine3(personalProfileDTO.getCompany().getAddressLine3());
			companyModel.setCity(personalProfileDTO.getCompany().getCity());
			companyModel.setCompanyEmail(personalProfileDTO.getCompany().getCompanyEmail());
			companyModel.setCountry(personalProfileDTO.getCompany().getCountry());
			companyModel.setInvoiceCode(personalProfileDTO.getCompany().getInvoiceCode());
			companyModel.setPostcode(personalProfileDTO.getCompany().getPostcode());
		} catch (DaoProfileException dpe) {
			throw dpe;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServiceProfileException(ErrorMessages.SERVICE_PERSONAL_PROFILE_CANT_CREATE);
		}
		return companyModel;
	}

	
	private String generateUniqueCode() throws DaoProfileException{
		
		String codeStr = RandomStringUtils.randomAlphanumeric(10);
		
		List<CompanyProfileDTO> allCompanies = this.companyProfileDAOImpl.getAll();
		
		for(CompanyProfileDTO company:allCompanies){
			if(codeStr.equalsIgnoreCase(company.getInvoiceCode())){
				generateUniqueCode();
			}
		}
		
		return codeStr;
		
	}
	
	
	
	public CompanyProfileDAO getCompanyProfileDAOImpl() {
		return companyProfileDAOImpl;
	}

	public void setCompanyProfileDAOImpl(CompanyProfileDAO companyProfileDAOImpl) {
		this.companyProfileDAOImpl = companyProfileDAOImpl;
	}
	
	public PersonalProfileService getPersonalProfileService() {
		return personalProfileService;
	}

	public void setPersonalProfileService(
			PersonalProfileService personalProfileService) {
		this.personalProfileService = personalProfileService;
	}
	
	public PersonalProfileDAO getPersonalProfileDAO() {
		return personalProfileDAO;
	}

	public void setPersonalProfileDAO(PersonalProfileDAO personalProfileDAO) {
		this.personalProfileDAO = personalProfileDAO;
	}
	
}




