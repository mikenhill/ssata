package com.salmon.ssata.backend.service;

import com.salmon.ssata.backend.BackendException;

/**
 * @author charles
 *
 */
public class ServiceException extends BackendException{
	
	private static final long serialVersionUID = 899878074499611218L;
    
    private String errorCode="SERVICE_EXCEPTION";
 
    public ServiceException(String message){
        super(message);
    }
    
    public ServiceException(String message, String errorCode){
        super(message);
        this.errorCode=errorCode;
    }  
     
    public String getErrorCode(){
        return this.errorCode;
    }

}