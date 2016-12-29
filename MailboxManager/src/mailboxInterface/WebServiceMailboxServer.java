package mailboxInterface;

import java.io.IOException;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

import ejb.ReadHosts;


public class WebServiceMailboxServer {
	public static void main(String[] args) {
		
		try {
			ReadHosts hosts = new ReadHosts();
	   	    String serverIP = hosts.readFile();
			String uri = "http://" + serverIP + ":9998/MailBoxServer/";
			HttpServer server = HttpServerFactory.create(uri);
			server.start();
			System.out.println("Browse the available operations with this URL : "+uri+"application.wadl"); 
			System.out.println("Press Enter to stop the server. ");
			System.in.read();
			System.out.println("Server stopped.");
			server.stop(0);
			} catch (IllegalArgumentException e) {
			e.printStackTrace(); } catch (IOException e) {
			e.printStackTrace(); }
	}

}
