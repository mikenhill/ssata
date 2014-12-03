package com.salmon.ssata.backend.dao.profile.service;

import java.util.List;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.dto.profile.CompanyProfileDTO;

public interface CompanyProfileDAO {

	public void createProfile(CompanyProfileDTO profileDTO)  throws DaoProfileException;
	public void updateProfile(CompanyProfileDTO profileDTO)  throws DaoProfileException;
	public List<CompanyProfileDTO> getAll() throws DaoProfileException;
	public CompanyProfileDTO getByInvoiceCode(String profileId);
	public CompanyProfileDTO getById(String id)  throws DaoProfileException;
	
}
