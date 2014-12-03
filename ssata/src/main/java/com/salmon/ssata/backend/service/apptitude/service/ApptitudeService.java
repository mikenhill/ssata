package com.salmon.ssata.backend.service.apptitude.service;

import java.util.List;

import com.salmon.ssata.backend.dao.profile.DaoProfileException;
import com.salmon.ssata.backend.dto.TestTypeDTO;
import com.salmon.ssata.backend.dto.profile.UserDetailsDTO;
import com.salmon.ssata.backend.service.profile.ServiceProfileException;
import com.salmon.ssata.web.forms.profile.RegistrationForm;
import com.salmon.ssata.web.models.GenericModel;
import com.salmon.ssata.web.models.profile.ProfileModel;

public interface ApptitudeService {

	public List<TestTypeDTO> getAllTestTypes() throws ServiceProfileException;
}
