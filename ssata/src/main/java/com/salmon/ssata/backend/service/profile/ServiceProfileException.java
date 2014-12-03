package com.salmon.ssata.backend.service.profile;

import com.salmon.ssata.backend.service.ServiceException;
import com.salmon.ssata.common.ErrorMessages;

/**
 * @author charles
 *
 */
public class ServiceProfileException extends ServiceException{
	
	private static final long serialVersionUID = 899878074499611218L;
    
    private String errorCode="SERVICE_PROFILE_EXCEPTION";
 
    public ServiceProfileException(ErrorMessages message){
        super(message.getMsgCode());
    }
    
    public ServiceProfileException(ErrorMessages message, String errorCode){
        super(message.getMsgCode());
        this.errorCode=errorCode;
    }  
     
    public String getErrorCode(){
        return this.errorCode;
    }

}
