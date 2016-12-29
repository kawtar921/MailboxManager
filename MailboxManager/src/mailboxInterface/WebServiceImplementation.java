package mailboxInterface;
import java.rmi.RemoteException;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;


@Path("/mailbox")
public class WebServiceImplementation {

	 @PUT
	 @Consumes("text/plain")   
	 @Path("/addUser") 
	 public void add(String name) {
	  	  try {
			MailboxCommands mailbox = new MailboxCommands();
			System.out.println("Adding user " + name + "...");
			mailbox.addUser(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	  
	 }
	 
	 @PUT
	 @Consumes("text/plain")   
	 @Path("/deleteUser") 
	 public void delete(String name) {
	  	  try {
			MailboxCommands mailbox = new MailboxCommands();
			System.out.println("Removing user " + name + "...");
			mailbox.removeUser(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	  
	 }
}
