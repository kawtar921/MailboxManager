package directoryInterface;

import java.rmi.RemoteException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/directory")
public class WebServiceImplementation {

	@GET 
	@Produces("text/plain")
	@Path("/getWriteRights/{name}")
	public String getWriteRights(@PathParam("name") String name) 
	{ 
		try {
			DirectoryCommands directory = new DirectoryCommands();
			List<Boolean> rights = directory.lookupAUserRights(name);
			System.out.println("Returning " + rights.get(1));
			if(rights.get(1))
				return "yes";
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "no";
		
	}

	@GET 
	@Produces("text/plain")
	@Path("/getReadRights/{name}")
	public String getReadRights(@PathParam("name") String name) 
	{ 
		try {
			DirectoryCommands directory = new DirectoryCommands();
			List<Boolean> rights = directory.lookupAUserRights(name);
			System.out.println("Returning " + rights.get(0));
			if(rights.get(0))
				return "yes";
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "no";
		
	}
}
