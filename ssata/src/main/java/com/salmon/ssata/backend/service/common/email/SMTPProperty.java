package com.salmon.ssata.backend.service.common.email;

/**
 * @author mdani
 *
 */


public interface SMTPProperty {

	public String getPrimaryEmail();
	
	public String getEmailVeriCode();
	
	public String getUsername();
	
	public String getTextTemplatePath();
	
	public String getHTMLTemplatePath();
	
	public String getSubject();
	
	public String[] getReceivers();
	public void setSMTPProerty(String recverEmail,String subject,
			String textTemplate,String htmlTemplate);
	
	public void setBccReceiver(String bccReceiver);
	
	public String getBccReceiver();
	
}
