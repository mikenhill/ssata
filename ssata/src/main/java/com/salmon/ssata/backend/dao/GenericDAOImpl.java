/**
 * 
 */
package com.salmon.ssata.backend.dao;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.salmon.ssata.backend.dto.GenericDTO;

/**
 * @author mhill
 *
 */
public class GenericDAOImpl extends HibernateDaoSupport implements ApplicationContextAware,GenericDAO{
	private Logger logger = Logger.getLogger("gosocial");
	private ApplicationContext applicationContext;
	private SessionFactory sessionFactory;
	
	protected final Logger log = Logger.getLogger(this.getClass());
	
	public GenericDAOImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
		this.sessionFactory = sessionFactory;
	}
	
	public ApplicationContext getApplicationContext() {
		getLogger().info(new Date());
		return applicationContext;
	}
	
	protected void setCreateAndModifiedDates(GenericDTO genericDTO) {
		getLogger().info(new Date());
		Date today=new Date();
		genericDTO.setDateModified(today);
		genericDTO.setDateCreated(today);
		getLogger().info(new Date());
	}
	protected Session getBaseSession() {
		getLogger().info(new Date());
		return sessionFactory.getCurrentSession();
	}
	
	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext){
		getLogger().info(new Date());
		this.applicationContext = applicationContext;
		getLogger().info(new Date());
	}

    public void update(GenericDTO dto) {
        getLogger().info(new Date());
        Date today = new Date();

        dto.setDateModified(today);

        this.getHibernateTemplate().update(dto);
        this.getHibernateTemplate().flush();
        getLogger().info(new Date());
    }

    public void updateAll(String query) {
        getLogger().info(new Date());

        this.getHibernateTemplate().bulkUpdate(query);
        this.getHibernateTemplate().flush();
        getLogger().info(new Date());
    }

	public void create(GenericDTO dto) {
		getLogger().info(new Date());
		Date today = new Date();

		dto.setDateModified(today);

		this.getHibernateTemplate().save(dto);
		this.getHibernateTemplate().flush();
		getLogger().info(new Date());
	}

	public void delete(GenericDTO dto) {
		getLogger().info(new Date());
		Date today = new Date();
		
		dto.setDateModified(today);
		
		this.getHibernateTemplate().delete(dto);
		this.getHibernateTemplate().flush();
		getLogger().info(new Date());
	}
	
	public Criteria getCriteria(Class className){
		
		return this.getSessionFactory().getCurrentSession().createCriteria(className);
	}

	public Logger getLogger() {
		return logger;
	}
}
