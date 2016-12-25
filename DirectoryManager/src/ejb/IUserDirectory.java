package ejb;

import javax.ejb.Remote;
import java.rmi.RemoteException;
import java.util.List;

@Remote
public interface IUserDirectory{

	public String addUser(String user)throws RemoteException;
	public boolean userExists(String user)throws RemoteException;
	public String removeUser(String user)throws RemoteException;
	public List<String> lookupAllUsers()throws RemoteException;
	public List<Boolean> lookupAUserRights(String user)throws RemoteException;
	public String updateAUserRights(String user, boolean read, boolean write)throws RemoteException;
}
