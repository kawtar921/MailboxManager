package entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("MailBox")
public class MailBox extends Box implements IMailBox{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	
	@Column(name="username")
	public String user;
	
	public MailBox(){}
	
	public MailBox(String boxName, String user) {
		super(boxName);
		this.user = user;
	}

	
	@Override
	public void deleteAMessage(String subject) {
		messages.remove(getMessageBySubject(subject));
	}

	@Override
	public void deleteReadMessages() {
		for(Message mess : messages)
		{
			if(mess.isAlreadyRead())
				messages.remove(mess);
		}
	}

	@Override
	public void deleteAllMessages() {
		messages.clear();
	}

	@Override
	public List<Message> readNewMessages() {
		
		List<Message> m = new ArrayList<Message>();
		for(Message mess : messages)
		{
			if(!mess.isAlreadyRead())
			{
				m.add(mess);
				mess.setAlreadyRead(true);
			}
				
		}
		return m;
	}


	public Collection<Message> getMessages() {
		return messages;
	}


	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}

	
	public Message getMessageBySubject(String subject)
	{
		Message m = null;
		for(Message mess : messages)
		{
			if(mess.getSubject().equals(subject))
				m = mess;
		}
		
		return m;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}
}
