package com.salmon.ssata.backend.service.common.email;

/**
 * @author mdani
 *
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.salmon.ssata.backend.dao.SMTPQueueItemDAO;
import com.salmon.ssata.backend.dto.SMTPQueueItemDTO;
import com.salmon.ssata.backend.service.GenericServiceImpl;

@Service
public class SMTPService extends GenericServiceImpl{
	private static final Logger log = Logger.getLogger(SMTPService.class);
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SMTPQueueItemDAO smtpQueueItemDAO;
	
	@Autowired
	private SMTPProperty smtpPropertyImpl;

	public SMTPProperty getSmtpPropertyImpl() {
		return smtpPropertyImpl;
	}

	public void setSmtpPropertyImpl(SMTPProperty smtpPropertyImpl) {
		this.smtpPropertyImpl = smtpPropertyImpl;
	}
	
	public String getDataString() {
		return null;
	}

	/**
	 * This method is used to send email of the given template and attached file with given subject to given user
	 * @param templateString It specifies template of email
	 * @param receiver It specifies the user to whom email will be sent
	 * @param subject It specifies subject for email
	 * @param fileSystemResource It specifies attached document for email
	 */

	public void sendMail(String[]templateString,String receiver, String subject, FileSystemResource fileSystemResource){
		List<FileSystemResource> files = new ArrayList<FileSystemResource>();
		files.add(fileSystemResource);
		
		this.sendMail(templateString,receiver,subject, files);
	}
	
	/**
	 * This method is used to send mail to given user of given template with given subject
	 * @param templateString It specifies template of email
	 * @param receiver It specifies user to whom email will be sent
	 * @param subject It specifies subject for email
	 */

	public void sendMail(String[]templateString,String receiver, String subject){
		this.sendMail(templateString, receiver, subject, new ArrayList<FileSystemResource>());
	}
	
	/**
	 * This method is used to send mail to given multiple user with multiple attached files with given subjects
	 * @param templateString It specifies template for email
	 * @param receiver It specifies first user to whom email will be sent
	 * @param bccReceiver It specifies second user to whom email will be sent
	 * @param subject It specifies subject for email
	 * @param files It specifies attached files for email
	 */

	public void sendMail(String[]templateString,String receiver, String subject, List<FileSystemResource> files){
		
		this.smtpPropertyImpl.setSMTPProerty(receiver,subject,templateString[0],templateString[1]);
		this.createMail(this.smtpPropertyImpl, files);
	}

	
	public void createMail(SMTPProperty smtpProperty) {
		this.createMail(smtpProperty, new ArrayList<FileSystemResource>());
	}
	
	public void createMail(SMTPProperty smtpProperty,FileSystemResource file) {
		List<FileSystemResource>files = new ArrayList<FileSystemResource>();
		files.add(file);
		this.createMail(smtpProperty, files);
	}

	public void createMail(SMTPProperty smtpProperty,List<FileSystemResource> files) {
		
		try{
			
			MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage,true);
			//msgHelper.setFrom(smtpProperty.getSender());
			msgHelper.setTo(smtpProperty.getReceivers());
			if(smtpProperty.getBccReceiver()!= null)
				msgHelper.setBcc(smtpProperty.getBccReceiver());
			msgHelper.setSubject(smtpProperty.getSubject());
			
			for(FileSystemResource fileSystemResource: files) {
				if(fileSystemResource != null) {
					msgHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
				}
			}
			
			msgHelper.setText(smtpProperty.getTextTemplatePath(), smtpProperty.getHTMLTemplatePath());
            addEmailToQueue(mimeMessage);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SMTPQueueItemDTO addEmailToQueue(MimeMessage email) {
		SMTPQueueItemDTO smtpQueueItem = null;
		try {
			smtpQueueItem = new SMTPQueueItemDTO();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			email.writeTo(os);
			smtpQueueItem.setMimeMessage(os.toByteArray());
			this.smtpQueueItemDAO.create(smtpQueueItem);
			this.smtpQueueItemDAO.getHibernateTemplate().flush();
		} catch (Exception e){
			//log.error("addEmailToQueue : " + e.getMessage());
		}
		
		return smtpQueueItem;
	}

	public void flushQueues() {
		log.debug("flushQueues");
		InputStream is = null;
		List<SMTPQueueItemDTO> list	= this.smtpQueueItemDAO.getNotSent();
		MimeMessage mimeMessage = null;
		for (SMTPQueueItemDTO item : list){
			is = new ByteArrayInputStream(item.getMimeMessage());
			mimeMessage = this.mailSender.createMimeMessage(is);
			if (mimeMessage != null) {
				try {
					this.mailSender.send(mimeMessage);
					item.setDateSent(new Date());
					item.setSent(true);
					this.smtpQueueItemDAO.update(item);
					
				} catch (Exception e) {
					log.error("flushQueues Message sending error: " + e.getMessage());
				}
			}
		}
		log.debug("flushQueues Complete!");
	}

	@Scheduled(fixedDelay = 10000)
	public void scheduledFlush() {
		//log.info("Scheduled flush" + new Date());
		this.flushQueues();
	}

}
