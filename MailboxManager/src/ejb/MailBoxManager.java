package ejb;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.MailBox;
import entity.Message;
import entity.NewsBox;

@Stateless(mappedName="ejb.IMailBoxManager")
public class MailBoxManager extends UnicastRemoteObject implements IMailBoxManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private NewsBox Newsbox;
	private List<MailBox> MailBoxes;
	
	@PersistenceContext(unitName="pu1")
    private EntityManager em;
	
	public MailBoxManager() throws RemoteException
	{
		MailBoxes = new ArrayList<MailBox>();
		Newsbox = new NewsBox("newsbox");
	}
	
	public MailBoxManager(NewsBox newsbox, List<MailBox> mailBoxes) throws RemoteException {
		super();
		Newsbox = newsbox;
		MailBoxes = mailBoxes;
	}
	
	@Override
	public List<String> readAUserNewMessages(String user) {
		List<String> messages = new ArrayList<String>();
		MailBox mailbox = getBoxOfUser(user);
		for(Message m : mailbox.readNewMessages())
		{
			messages.add(m.getSenderName()+":"+m.getReceiverName()+":"+m.getSendingDate()+":"+m.getSubject()+":"+m.getBody());
		}
		return messages;
	}
	
	
	@Override
	public List<String> readAUserAllMessages(String user) {
		List<String> messages = new ArrayList<String>();
		MailBox mailbox = getBoxOfUser(user);
		for(Message m : mailbox.readAllMessages())
		{
			messages.add(m.getSenderName()+":"+m.getReceiverName()+":"+m.getSendingDate()+":"+m.getSubject()+":"+m.getBody());
		}
		return messages;
	}
	
	
	@Override
	public String deleteAUserMessage(String user, String mess) {
		MailBox mailbox = getBoxOfUser(user);
		mailbox.deleteAMessage(mess);
		
		return "Message deleted!";
	}
	
	
	@Override
	public String deleteAUserReadMessages(String user) {
        Query query = em.createNativeQuery("SELECT id from BOX b WHERE b.username='" + user + "'");
        long id = (long)query.getSingleResult();
        query = em.createNativeQuery("DELETE from MESSAGE WHERE BOX_ID=" + id + " AND ALREADYREAD=1");
        query.executeUpdate();
        return "messages deleted!";
	}
	
	@Override
	public void addUser(String user) {
		MailBox box = new MailBox(user+"_box",user);
		//local
		MailBoxes.add(box);
		Newsbox.addUser(user);
		//persistence
		em.persist(box);
		NewsBox newsBox = getNewsBox();
		newsBox.addUser(user);
		System.out.println("User : " + user + " added");
	}
	
	
	@Override
	public void removeUser(String user) {
		MailBoxes.remove(getBoxOfUser(user));
		Newsbox.removeUser(user);
		//persistence
		em.remove(getBoxOfUser(user));
		System.out.println("User : " + user + " deleted");
	}
	
	@Override
	public String sendAmessageToABox(String sender, Message mess) {
		
		String receiver = mess.getReceiverName();
		try {
			if(userExists(receiver))
			{
				MailBox mail = getBoxOfUser(receiver);
				mail.addMessage(mess);
				mess.setBox(mail);
				return "Message sent!";
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return "Message not sent, Receiver doesn't exit!";
	}
	
	
	@Override
	public String sendNews(String user, Message mess) {
	   //Webservice
		try {
		WebServiceMailboxClient webservice = new WebServiceMailboxClient();
		if(webservice.getWriteRightsForUser(user))
		{
			   NewsBox newsBox = getNewsBox();
			   newsBox.addMessage(mess);
			   mess.setBox(newsBox);
			   return "News sent!";
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return "You don't have enough rights!";
		
	}
	
	
	public void getAllUsers()
	{
		System.out.println("Maibox users");
		for(MailBox mail : MailBoxes)
		{
			System.out.println("name : " + mail.getUser());
		}
		
		System.out.println("NewBox users");
		for(String name : Newsbox.getUsers())
		{
			System.out.println("name : " + name);
		}
	}

	public NewsBox getNewsbox() {
		return Newsbox;
	}

	public void setNewsbox(NewsBox newsbox) {
		Newsbox = newsbox;
	}

	public List<MailBox> getMailBoxes() {
		return MailBoxes;
	}

	public void setMailBoxes(List<MailBox> mailBoxes) {
		MailBoxes = mailBoxes;
	}
	
	@Override
	public boolean userExists(String name) throws RemoteException {
		Query query = em.createNativeQuery("SELECT COUNT(*) from BOX b WHERE b.username='" + name + "'"); 
		return (long)query.getSingleResult()>0;
	}

	private MailBox getBoxOfUser(String username) {
		Query query = em.createNativeQuery("SELECT * from BOX b WHERE b.username='" + username + "'",MailBox.class);
		return (MailBox) query.getSingleResult();
	}

	@Override
	public void addNewsBox() throws RemoteException {
		NewsBox news = new NewsBox("NewsBox");
		em.persist(news);
		
	}

	@Override
	public List<String> readNews(String username) throws RemoteException {
		List<String> messages = new ArrayList<String>();
		//webservice
		try {
			WebServiceMailboxClient webservice = new WebServiceMailboxClient();
			
			if(webservice.getReadRightsForUser(username))
			{
				NewsBox newsBox = getNewsBox();
				for(Message m : newsBox.readAllMessages())
				{
					messages.add(m.getSenderName()+":"+m.getReceiverName()+":"+m.getSendingDate()+":"+m.getSubject()+":"+m.getBody());
				}
				
			}else
			{
				messages.add("ERROR");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messages;
		
	}
	
	private NewsBox getNewsBox()
	{
		Query query = em.createQuery("SELECT b from Box b WHERE TYPE(b) = :type");
		query.setParameter("type", NewsBox.class);
		return (NewsBox) query.getResultList().get(0);
	}
	

}
