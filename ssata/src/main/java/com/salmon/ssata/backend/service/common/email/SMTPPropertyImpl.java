package com.salmon.ssata.backend.service.common.email;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author mdani
 *
 */

@Component
//@Scope("session")
public class SMTPPropertyImpl implements SMTPProperty {

	private String primaryEmail;
	private String varificationCode;
	private String username;
	private String[] receivers;
	private String bccReceiver;
	private String subject;
	private String textTemplatePath;
	private String htmlTemplatePath;

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	@Override
	public String getPrimaryEmail() {
		return this.primaryEmail;
	}
	
	public void setEmailVerificationCode(String varificationCode) {
		this.varificationCode = varificationCode;
	}

	@Override
	public String getEmailVeriCode() {
		return this.varificationCode;
	}
	
	public void setUsername(String userName) {
		this.username = userName;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	
	
	public void setTextTemplatePath(String textTemplatePath) {
		this.textTemplatePath = textTemplatePath;
	}

	@Override
	public String getTextTemplatePath() {
		return textTemplatePath;
	}

	public void setHTMLTemplatePath(String htmlTemplatePath) {
		this.htmlTemplatePath = htmlTemplatePath;
	}
	
	@Override
	public String getHTMLTemplatePath() {
		return htmlTemplatePath;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Override
	public String getSubject() {
		return subject;
	}

	public void setReceivers(String [] receivers) {
		this.receivers = receivers;
	}
	
	@Override
	public String[] getReceivers() {
		return receivers;
	}
	
	public void setSMTPProerty(String receiverEmail,String subject,
			String textTemplate,String htmlTemplate){
		this.receivers = new String[] {receiverEmail};
		this.subject = subject;
		this.textTemplatePath = textTemplate;
		this.htmlTemplatePath = htmlTemplate;
	}
	public void setBccReceiver(String bccReceiver) {
		this.bccReceiver = bccReceiver;
	}
	public String getBccReceiver() {
		return bccReceiver;
	}	
		
}
