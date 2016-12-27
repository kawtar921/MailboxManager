package mailboxInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.IMailBoxManager;
import entity.Message;

public class MailboxCommands extends UnicastRemoteObject implements IMailboxCommands{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static IMailBoxManager sb;
	
	protected MailboxCommands() throws RemoteException {
		super();
		InitialContext ic;
		try {
			ic = new InitialContext();
			sb = (IMailBoxManager) ic.lookup("ejb.IMailBoxManager");
			sb.addNewsBox();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String sendMessage(String username, String receiver, String sendingDate, String subject, String body)
			throws RemoteException {
		Message mess = new Message(username, receiver, sendingDate, subject, body);
		return sb.sendAmessageToABox(username, mess);
	}

	@Override
	public List<String> readNewMessages(String username) throws RemoteException {
		return sb.readAUserNewMessages(username);
	}

	@Override
	public List<String> readAllMessages(String username) throws RemoteException {
		return sb.readAUserAllMessages(username);
	}

	@Override
	public String removeReadMessages(String username) throws RemoteException {
		return sb.deleteAUserReadMessages(username);
	}

	@Override
	public String removeMessage(String username, String subject) throws RemoteException {
		return sb.deleteAUserMessage(username, subject);
	}

	@Override
	public String sendNews(String username, String sendingDate, String subject, String body) throws RemoteException {
		Message mess = new Message(username, null, sendingDate, subject, body);
		return sb.sendNews(username, mess);
	}

	@Override
	public List<String> readNews() throws RemoteException {
		return sb.readNews();
	}

}
