package ejb;

import java.io.IOException;
import java.net.URI;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class WebServiceDirectoryClient {
	public static WebResource service;
	
	public WebServiceDirectoryClient() throws IOException
	{
		Client client = Client.create(new DefaultClientConfig()); 
		ReadHosts hosts = new ReadHosts();
   	    String serverIP = hosts.readFile();
		String MailBox_Uri = "http://"+serverIP+":9998/MailBoxServer";
		URI uri = URI.create(MailBox_Uri);
		service = client.resource(uri);
	}
	
	public void addUser(String user)
	{
		service.path("mailbox/addUser").put(user);
	}
	
	public void removeUser(String user)
	{
		 service.path("mailbox/deleteUser").put(user);
	}
}
