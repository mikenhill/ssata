package com.salmon.ssata.backend.dao.profile;

import com.salmon.ssata.backend.dao.DaoException;
import com.salmon.ssata.common.ErrorMessages;

/**
 * @author charles
 *
 */
public class DaoProfileException  extends DaoException{
	
	
	private static final long serialVersionUID = 867093174499611218L;
    
    private String errorCode="DAO_PROFILE_EXCEPTION";
 
    public DaoProfileException(ErrorMessages message){
        super(message.getMsgCode());
    }
    
    public DaoProfileException(ErrorMessages message, String errorCode){
        super(message.getMsgCode());
        this.errorCode=errorCode;
    }  
     
    public String getErrorCode(){
        return this.errorCode;
    }
}
