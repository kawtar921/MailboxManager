package directoryInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadHosts {

	private static final String FILENAME = "hosts.txt";
	private BufferedReader br;
	
	public ReadHosts() throws FileNotFoundException
	{
		br = new BufferedReader(new FileReader(FILENAME));
	}
	
	public String readFile() throws IOException
	{
		String ServerIP = "";
	    String host;
			
			while ((host = br.readLine()) != null) {
				if(!host.equals(""))
				{
					ServerIP = host;
					break;
				}
			}
		
		return ServerIP;
	}
}
