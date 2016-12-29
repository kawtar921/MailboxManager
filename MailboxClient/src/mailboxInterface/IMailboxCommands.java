package mailboxInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IMailboxCommands extends Remote{

	public String sendMessage(String username,String receiver,String sendingDate,String subject,String body) throws RemoteException;
	public List<String> readNewMessages(String username)throws RemoteException;
	public List<String> readAllMessages(String username)throws RemoteException;
	public String removeReadMessages(String username)throws RemoteException;
	public String removeMessage(String username,String subject)throws RemoteException;
	public String sendNews(String username, String sendingDate, String subject, String body) throws RemoteException;
	public List<String> readNews(String username)throws RemoteException;
}
