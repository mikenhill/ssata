package com.salmon.ssata.backend.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author mdani
 *
 */

@Entity
@Table(name="SMTPMailQueue")
public class SMTPQueueItemDTO extends GenericDTO{
	
	private static final long serialVersionUID = 1;

	private byte[] mimeMessage = null;
	private	boolean sent;
	private Date dateSent;
	
	@Lob
	public byte[] getMimeMessage() {
		return mimeMessage;
	}
	public void setMimeMessage(byte[] mimeMessage) {
		this.mimeMessage = mimeMessage;
	}

	@Column( name = "sent" )
	public boolean isSent() {
		return sent;
	}
	public void setSent(boolean sent) {
		this.sent = sent;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateSent() {
		return dateSent;
	}
	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

}
