package entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String senderName;
	
	private String receiverName;

	private String sendingDate;
	
	private String subject;
	
	private String body;
	
	private boolean alreadyRead;
	
	@ManyToOne
	private Box box;
	
	public Message(){}
	
	public Message(String senderName, String receiverName, String sendingDate, String subject, String body) {
		this.senderName = senderName;
		this.receiverName = receiverName;
		this.sendingDate = sendingDate;
		this.subject = subject;
		this.body = body;
		alreadyRead = false;
	}

	public Box getBox() {
		return box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getSendingDate() {
		return sendingDate;
	}

	public void setSendingDate(String sendingDate) {
		this.sendingDate = sendingDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isAlreadyRead() {
		return alreadyRead;
	}

	public void setAlreadyRead(boolean alreadyRead) {
		this.alreadyRead = alreadyRead;
	}
	
	@Override
	public String toString() {
		return "Sender : " + senderName + " , receiver : " + receiverName + " , date : " + sendingDate + " , subject : " 
				+ subject + " , body : " + body;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof Message))return false;
	    Message user = (Message)obj;
	    return (user.getId() == this.getId());
	}
	
	

}
