package mailboxInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIDirectoryServer {

	public static Registry reg;
	public static void main(String[] args) {
		
		System.setProperty("java.security.policy","file:security.policy"); 
		if (System.getSecurityManager() == null) {
	            System.setSecurityManager(new SecurityManager());
	        }
	        
		try {
			
			reg = LocateRegistry.createRegistry(1098);
			MailboxCommands mailbox = new MailboxCommands();
			reg.rebind("Mailbox", mailbox);
			System.out.println("Server on!");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
