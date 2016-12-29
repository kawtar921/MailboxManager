package mailboxInterface;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class ClientCommands {

	public static IMailboxCommands mailbox;
	public static Registry registry;
	
	public ClientCommands() throws NotBoundException, IOException
	{
		System.setProperty("java.security.policy","file:security.policy");
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
		  ReadHosts hosts = new ReadHosts();
    	  String serverIP = hosts.readFile();
	      String name = "Mailbox";
	      registry = LocateRegistry.getRegistry(serverIP,1097);
	      mailbox = (IMailboxCommands) registry.lookup(name);
	}
	
	public String sendMessage(String username,String receiver,String sendingDate,String subject,String body) throws RemoteException
	{
		return mailbox.sendMessage(username, receiver, sendingDate, subject, body);
	}
	
	public List<String> readNewMessages(String username) throws RemoteException
	{
		return mailbox.readNewMessages(username);
	}
	
	public List<String> readAllMessages(String username) throws RemoteException
	{
		return mailbox.readAllMessages(username);
	}
	
	public String removeReadMessages(String username) throws RemoteException
	{
		return mailbox.removeReadMessages(username);
	}
	
	public String removeMessage(String username, String subject) throws RemoteException
	{
		return mailbox.removeMessage(username, subject);
	}

	public String sendNews(String username, String sendingDate, String subject, String body) throws RemoteException{
		return mailbox.sendNews(username,sendingDate,subject,body);
	}
	
	public List<String> readNews(String username) throws RemoteException
	{
		return mailbox.readNews(username);
	}
}
