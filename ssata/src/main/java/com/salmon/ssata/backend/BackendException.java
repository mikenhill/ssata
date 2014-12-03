package com.salmon.ssata.backend;

public class BackendException extends Exception{
	
	private static final long serialVersionUID = 7865432174499611218L;
    
    private String errorCode="BACKEND_EXCEPTION";
 
    public BackendException(String message){
        super(message);
    }
    
    public BackendException(String message, String errorCode){
        super(message);
        this.errorCode=errorCode;
    }  
     
    public String getErrorCode(){
        return this.errorCode;
    }
}
