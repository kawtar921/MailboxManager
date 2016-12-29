package directoryInterface;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.IUserDirectory;
import ejb.WebServiceDirectoryClient;

public class DirectoryCommands extends UnicastRemoteObject implements IDirectoryCommands{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static IUserDirectory sb;
    private WebServiceDirectoryClient webservice;
    
	protected DirectoryCommands() throws RemoteException {
		super();
		InitialContext ic;
		try {
			ic = new InitialContext();
			sb = (IUserDirectory) ic.lookup("ejb.IUserDirectory");
			webservice = new WebServiceDirectoryClient();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public String addUser(String user) throws RemoteException {
		//webservice
		webservice.addUser(user);
		return sb.addUser(user);
	}

	@Override
	public boolean userExists(String user) throws RemoteException {
		return sb.userExists(user);
	}

	@Override
	public String removeUser(String user) throws RemoteException {
		//webservice
		webservice.removeUser(user);
		return sb.removeUser(user);
	}

	@Override
	public List<String> lookupAllUsers() throws RemoteException {
		return sb.lookupAllUsers();
	}

	@Override
	public List<Boolean> lookupAUserRights(String user) throws RemoteException {
		return sb.lookupAUserRights(user);
	}

	@Override
	public String updateAUserRights(String user, boolean read, boolean write) throws RemoteException {
		return sb.updateAUserRights(user, read, write);
	}
	
	

}
