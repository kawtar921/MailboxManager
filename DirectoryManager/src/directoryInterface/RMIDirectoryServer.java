package directoryInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.UserDirectory;




public class RMIDirectoryServer {

	public static Registry reg;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 /*System.setProperty("java.security.policy","file:security.policy");
		 System.setProperty("java.rmi.server.codebase", IUserDirectory.class.getProtectionDomain().getCodeSource().getLocation().toString());
	     System.out.println(IUserDirectory.class.getProtectionDomain().getCodeSource().getLocation().toString());   */
		System.setProperty("java.security.policy","file:security.policy"); 
		if (System.getSecurityManager() == null) {
	            System.setSecurityManager(new SecurityManager());
	        }
	        
		try {
			
			
			reg = LocateRegistry.createRegistry(1098);
			DirectoryCommands directory = new DirectoryCommands();
			reg.rebind("Directory", directory);
			System.out.println("Serveur lancé");
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
