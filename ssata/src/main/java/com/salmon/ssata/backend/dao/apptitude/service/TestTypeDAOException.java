package com.salmon.ssata.backend.dao.apptitude.service;

import com.salmon.ssata.backend.dao.DaoException;
import com.salmon.ssata.common.ErrorMessages;

/**
 * @author mhill
 *
 */
public class TestTypeDAOException  extends DaoException{
	
	
	private static final long serialVersionUID = 867093174499611218L;
    
    private String errorCode="DAO_PROFILE_EXCEPTION";
 
    public TestTypeDAOException(ErrorMessages message){
        super(message.getMsgCode());
    }
    
    public TestTypeDAOException(ErrorMessages message, String errorCode){
        super(message.getMsgCode());
        this.errorCode=errorCode;
    }  
     
    public String getErrorCode(){
        return this.errorCode;
    }
}
