package com.salmon.ssata.common;

public enum ErrorMessages {

	DAO_UNABLE_TO_CREATE_TESTTYPE("com.salmon.ssata.dao.testType.cantCreate"),
	DAO_USER_DETAILS_ERROR_USERID("com.salmon.ssata.dao.userDetails.errorWithUserId"),
	DAO_USER_DETAILS_CANT_CREATE("com.salmon.ssata.dao.userDetails.cantCreate"),
	DAO_PERSONAL_PROFILE_CANT_PERSIST("com.salmon.ssata.dao.personalProfile.contPersist"),
	DAO_PERSONAL_PROFILE_CANT_CREATE("com.salmon.ssata.dao.personalProfile.cantCreate"),
	DAO_PERSONAL_PROFILE_USERNAME_DATA_NOT_FOUND("com.salmon.ssata.dao.personalProfile.userNameDataNotFound"),
	DAO_PERSONAL_PROFILE_USERNAME_USERDATA_NOT_FOUND("com.salmon.ssata.dao.personalProfile.userNameUserDataNotFound"),
	DAO_PERSONAL_PROFILE_ERROR_USERNAME("com.salmon.ssata.dao.personalProfile.errorWithUserName"),
	DAO_PERSONAL_PROFILE_USEROBJ_NOT_FOUND("com.salmon.ssata.dao.personalProfile.userObjDataNotFound"),
	DAO_PERSONAL_PROFILE_ERROR_USEROBJ("com.salmon.ssata.dao.personalProfile.errorWithUserObj"),
	DAO_PERSONAL_PROFILE_ERROR_COMPANY_ID("com.salmon.ssata.dao.personalProfile.errorCompanyId"),
	
	DAO_COMP_PROFILE_CANT_PERSIST("com.salmon.ssata.dao.companyProfile.cantPersist"),
	DAO_COMP_PROFILE_CANT_CREATE("com.salmon.ssata.dao.companyProfile.cantCreate"),
	DAO_COMP_PROFILE_USERNAME_DATA_NOT_FOUND("com.salmon.ssata.dao.companyProfile.userNameDataNotFound"),
	DAO_COMP_PROFILE_USERNAME_USERDATA_NOT_FOUND("com.salmon.ssata.dao.companyProfile.userNameUserDataNotFound"),
	DAO_COMP_PROFILE_ERROR_USERNAME("com.salmon.ssata.dao.companyProfile.errorWithUserName"),
	DAO_COMP_PROFILE_USEROBJ_NOT_FOUND("com.salmon.ssata.dao.companyProfile.userObjDataNotFound"),
	DAO_COMP_PROFILE_ERROR_USEROBJ("com.salmon.ssata.dao.companyProfile.errorWithUserObj"),
	DAO_COMP_PROFILE_ERROR_INVOICE("com.salmon.ssata.dao.companyProfile.errorWithInvoiceNo"),
	DAO_COMP_PROFILE_ERROR_ID("com.salmon.ssata.dao.companyProfile.errorWithId"),
	DAO_COMP_INVOICE_MATCH_ERROR("com.salmon.ssata.dao.companyProfile.errorInvoiceCodeMatch"),	
	
	SERVICE_PAGE_CANT_CREATE("com.salmon.ssata.service.page.cantCreate"),
	SERIVCE_PAGE_ERROR_USEROBJ("com.salmon.ssata.service.page.errorwithUserObj"),
	SERVICE_PAGE_DATA_NOT_FOUND("com.salmon.ssata.dao.page.pageNameDataNotFound"),
	SERVICE_COMP_PROFILE_CANT_CREATE("com.salmon.ssata.service.companyProfile.cantCreate"),
	SERVICE_PERSONAL_PROFILE_CANT_CREATE("com.salmon.ssata.service.personalProfile.cantCreate"),
	SERIVCE_PERSONAL_PROFILE_ERROR_USEROBJ("com.salmon.ssata.service.personalProfile.errorwithUserObj"),
	SERVICE_REGISTER_CANT_REGISTER("com.salmon.ssata.service.registeraton.cantRegister"),
	SERVICE_USER_ROLE_ERROR("com.salmon.ssata.service.user.roleError"),
	SERVICE_USER_CANT_CREATE("com.salmon.ssata.service.user.cantCreate"),
	SERVICE_USER_ERROR_ASSEMBLE("com.salmon.ssata.service.user.errorAssemble"),
	
	SERVICE_REGISTER_ERROR_VERIFY_INCOMPLETE("com.salmon.ssata.service.registration.incompleteVerification"),
	SERVICE_REGISTER_ERROR_VERIFY_BADDATA("com.salmon.ssata.service.registration.cantVefiry"),
	
	DAO_USER_DETAILS_ERROR_USERNAME("com.salmon.ssata.dao.userDetails.errorWithUserName");

	private String msgCode;
	
	private ErrorMessages(String s){
		msgCode = s;
	}

	/**
	 * @return the msgCode
	 */
	public String getMsgCode() {
		return msgCode;
	}
	
}
