package com.salmon.ssata.backend.dao.profile.service;

import java.util.List;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.dto.profile.PersonalProfileDTO;
import com.salmon.ssata.backend.dto.profile.UserDetailsDTO;

public interface PersonalProfileDAO {

	public void createProfile(PersonalProfileDTO profileDTO) throws DaoProfileException;
	public PersonalProfileDTO getByUsername(String userName) throws DaoProfileException;
	public PersonalProfileDTO getByUser(UserDetailsDTO user) throws DaoProfileException;
	public PersonalProfileDTO getById(String id) throws DaoProfileException;
	public void updateProfile(PersonalProfileDTO profileDTO) throws DaoProfileException;
	public List<PersonalProfileDTO> getProfilesByCompnayId(String companyProfileId) throws DaoProfileException;
	
}
