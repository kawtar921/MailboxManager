package directoryInterface;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class ClientCommands{

	public static IDirectoryCommands directory;
	public static Registry registry;
	
	public ClientCommands() throws NotBoundException, IOException
	{
		System.setProperty("java.security.policy","file:security.policy");
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
		  ReadHosts hosts = new ReadHosts();
    	  String serverIP = hosts.readFile();
	      String name = "Directory";
	      registry = LocateRegistry.getRegistry(serverIP,1098);
          directory = (IDirectoryCommands) registry.lookup(name);
	}
	
	public String addUser(String username) throws RemoteException
	{
		return directory.addUser(username);
	}
	
	public String removeUser(String username) throws RemoteException
	{
		return directory.removeUser(username);
	}
	
	
	public boolean userExists(String username) throws RemoteException
	{
		return directory.userExists(username);
	}

	public List<String> lookupAllUsers() throws RemoteException {
		return directory.lookupAllUsers();
	}

	public List<Boolean> lookupAUserRights(String user) throws RemoteException {
		return directory.lookupAUserRights(user);
	}

	public String updateAUserRights(String user, boolean read, boolean write) throws RemoteException {
		return directory.updateAUserRights(user, read, write);
	}
	
	

	
	
}
