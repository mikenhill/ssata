package com.salmon.ssata.backend.dto.profile;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.salmon.ssata.backend.dto.GenericDTO;

/**
 * @author mdani
 *
 */

@Entity
@Table(name = "personal_profile_details")
public class PersonalProfileDTO extends GenericDTO{

	private static final long serialVersionUID = 1L;

	private UserDetailsDTO user;
	private String firstName;
	private String lastName;
	private CompanyProfileDTO company;
	private String currency;

	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "id", nullable = true)
	public CompanyProfileDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyProfileDTO company) {
		this.company = company;
	}

	@OneToOne
	public UserDetailsDTO getUser() {
		return user;
	}

	public void setUser(UserDetailsDTO user) {
		this.user = user;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PersonalProfileDTO)) {
			return false;
		}
		PersonalProfileDTO profile = (PersonalProfileDTO) obj;
		if (profile.getId().equals(this.getId())) {
			return true;
		}
		return super.equals(obj);
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
