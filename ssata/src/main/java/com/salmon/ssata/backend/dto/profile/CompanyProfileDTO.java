package com.salmon.ssata.backend.dto.profile;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.salmon.ssata.backend.dto.GenericDTO;

/**
 * @author mdani
 *
 */

@Entity
@Table(name = "company_profile_details")
public class CompanyProfileDTO extends GenericDTO{

	private static final long serialVersionUID = 1L;

//	private PersonalProfileDTO profile;
	
	
	private Set<PersonalProfileDTO> profile;
	
	private String companyName;
	private String companyEmail;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String country;
	private String postcode;
	private String invoiceCode;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<PersonalProfileDTO> getProfile() {
		return profile;
	}

	public void setProfile(Set<PersonalProfileDTO> profile) {
		this.profile = profile;
	}
	
	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

//	@OneToOne
//	public PersonalProfileDTO getProfile() {
//		return profile;
//	}
//
//	public void setProfile(PersonalProfileDTO profile) {
//		this.profile = profile;
//	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CompanyProfileDTO)) {
			return false;
		}
		CompanyProfileDTO profile = (CompanyProfileDTO) obj;
		if (profile.getId().equals(this.getId())) {
			return true;
		}
		return super.equals(obj);
	}
	
}
