package ejb;

import javax.ejb.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.Message;

@Remote
public interface IMailBoxManager{

	public List<String> readAUserNewMessages(String user)throws RemoteException;
	public List<String> readAUserAllMessages(String user)throws RemoteException;
	public String deleteAUserMessage(String user, String m)throws RemoteException;
	public String deleteAUserReadMessages(String user)throws RemoteException;
	public String sendAmessageToABox(String user, Message mess)throws RemoteException;
	public void addUser(String user)throws RemoteException;
	public void removeUser(String user)throws RemoteException;
	public String sendNews(String sender, Message mess)throws RemoteException;
	public boolean userExists(String name)throws RemoteException;
	public void addNewsBox()throws RemoteException;
	public List<String> readNews(String username) throws RemoteException;
}
