package ejb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class WebServiceMailboxClient {
	public static WebResource service;
	public WebServiceMailboxClient() throws IOException
	{
		Client client = Client.create(new DefaultClientConfig()); 
		/*ReadHosts hosts = new ReadHosts();
   	    String serverIP = hosts.readFile();*/
		String MailBox_Uri = "http://192.168.1.5:9997/DirectoryServer/";
		URI uri = URI.create(MailBox_Uri);
		service = client.resource(uri);
	}
	
	public boolean getReadRightsForUser(String name)
	{
		URL url;
		String response = "";
		try {
			url = new URL("http://192.168.1.5:9997/DirectoryServer/directory/getReadRights/" + name);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "text/plain");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			response = br.readLine();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//String response = service.path("directory/getReadRights/"+ name).accept(MediaType.TEXT_PLAIN).get(String.class);
		return response.equals("yes");
	}
	
	public boolean getWriteRightsForUser(String name)
	{
		//String response = service.path("directory/getWriteRights/"+ name).accept(MediaType.TEXT_PLAIN).get(String.class);
		
		URL url;
		String response = "";
		try {
			url = new URL("http://192.168.1.5:9997/DirectoryServer/directory/getWriteRights/" + name);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "text/plain");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			response = br.readLine();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.equals("yes");
	}
}
