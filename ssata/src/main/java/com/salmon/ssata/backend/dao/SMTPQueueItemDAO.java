package com.salmon.ssata.backend.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.salmon.ssata.backend.dto.SMTPQueueItemDTO;

/**
 * @author mdani
 *
 */

@Repository
public class SMTPQueueItemDAO extends GenericDAOImpl{

	protected final Logger log = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	public SMTPQueueItemDAO(SessionFactory sessionFactory){
		super(sessionFactory);
	}
	
	/**
	 * Creates a new record in the database for the given SMTPQueueItemDTO
	 * 
	 * @param smtpQueueItemDTO
	 */
	public void create(SMTPQueueItemDTO smtpQueueItemDTO) {
		log.debug("Create new SMTPQueueItem");
		try {
			this.getHibernateTemplate().save(smtpQueueItemDTO);//.create(smtpQueueItemDTO);
			this.getHibernateTemplate().flush();
		} catch (Exception e) {
			log.error("create : " + e.getMessage());
		}
	}
	
	
	/**
	 * Updates a record in the database that matched the given SMTPQueueItemDTO
	 * 
	 * @param smtpQueueItemDTO
	 */
	public void update(SMTPQueueItemDTO smtpQueueItemDTO) {
		log.debug("Update smtpQueueItemDTO");
		try {
			this.getHibernateTemplate().update(smtpQueueItemDTO);
			this.getHibernateTemplate().flush();
		} catch (Exception e) {
			log.error("update : " + e.getMessage());
		}
	}
	
	
	/**
	 * Returns a list of all unsent email's in the mail queue
	 * 
	 * @return List<SMTPQueueItemDTO> 	List of all unsent emails in the queue
	 */
	@SuppressWarnings("unchecked")
	public List<SMTPQueueItemDTO> getNotSent() {
		List<SMTPQueueItemDTO> mailList = null;
		
		mailList = (List<SMTPQueueItemDTO>) this.getHibernateTemplate().find("from SMTPQueueItemDTO where sent=?", false);
		
		return mailList;
	}
	
	
	/**
	 * Returns a list of all sent email's in the mail queue
	 * 
	 * @return List<SMTPQueueItemDTO> 	List of all sent emails in the queue
	 */
	@SuppressWarnings("unchecked")
	public List<SMTPQueueItemDTO> getSent() {
		List<SMTPQueueItemDTO> mailList = null;
		
		mailList = (List<SMTPQueueItemDTO>) this.getHibernateTemplate().find("from SMTPQueueItemDTO where sent=?", true);
		
		return mailList;
	}
	
}
