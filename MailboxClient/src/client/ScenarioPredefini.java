package client;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mailboxInterface.ClientCommands;

public class ScenarioPredefini {

	public static void main(String[] args) {
		
		try {
			ClientCommands mailbox = new ClientCommands();
			String sendingDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
			
			//Step 1 : Send A message
			System.out.println("Step 1 : Sending a message");
			System.out.println("----------------------------");
			System.out.println("Alpha sending message to Beta...");
			System.out.println(mailbox.sendMessage("Alpha", "Beta", sendingDate, "ASR6", "Bonjour, J'ai fini la partie du client directory. A demain!"));
			System.out.println("Alpha sending message to Beta...");
			System.out.println(mailbox.sendMessage("Alpha", "Beta", sendingDate, "ASR6", "Bonjour, Je n'ai pas encore fini la partie du manager directory. A demain!"));
			
			//Step 2 : Send news
			System.out.println("Step 2 : Sending news");
			System.out.println("----------------------------");
			System.out.println("Alpha sending News...");
			System.out.println(mailbox.sendNews("Alpha", sendingDate, "Soutenance ASR6", "Bonjour, La soutenance aura lieu dans la salle B313. Bonne chance!"));
			System.out.println("Beta sending News (Expected to fail)...");
			System.out.println(mailbox.sendNews("Beta", sendingDate, "Soutenance ASR6", "Bonjour, La soutenance aura lieu dans la salle B313. Bonne chance!"));
			
			//Step 3 : Read messages
			System.out.println("Step 3 : Reading messages of Beta");
			System.out.println("----------------------------");
			List<String> liste = mailbox.readAllMessages("Beta");
			if(liste.isEmpty())
			{
				System.out.println("No messages !");
				return;
			}

			System.out.println("List of messages : ");
			int i = 1;
			for(String s : liste)
			{
				System.out.println("Message number " + i + " : ");
				String[] elements = s.split(":");
				System.out.println("FROM : " + elements[0]);
				System.out.println("TO : " + elements[1]);
				System.out.println("Date : " + elements[2]);
				System.out.println("SUBJECT : " + elements[3]);
				System.out.println("\n----------------------------\n");
				System.out.println(elements[4]);
				System.out.println("\n----------------------------\n");
				i++;
			}
			
			//Step 4 : Read News
			System.out.println("Step 4 : Reading News of Beta");
			System.out.println("----------------------------");
			readNews("Beta",mailbox);
			System.out.println("Step 4Bis : Reading News of Alpha (Expected to fail)");
			System.out.println("----------------------------");
			readNews("Alpha",mailbox);
			
			//Step 5 : Remove read Messages
			System.out.println("Step 5 : Reamove all read messages of Beta");
			System.out.println("----------------------------");
			System.out.println(mailbox.removeReadMessages("Beta"));
			
			//Step 6 : Error cases
			System.out.println("\n\nStep 6 : Error Cases");
			System.out.println("----------------------------");
			System.out.println("Send message to non-existing user : ");
			System.out.println(mailbox.sendMessage("Alpha", "Gamma", sendingDate, "ASR6", "Bonjour, J'ai fini la partie du client directory. A demain!"));
			
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void readNews(String username, ClientCommands mailbox) throws RemoteException
	{
		List<String> liste = mailbox.readNews(username);
		if(liste.isEmpty())
		{
			System.out.println("No news !");
			return;
		}
		
		if(liste.get(0).equals("ERROR"))
		{
			System.out.println("You don't have enough rights!");
			return;
		}

		System.out.println("List of news : ");
		int i = 1;
		for(String s : liste)
		{
			System.out.println("News number " + i + " : ");
			String[] elements = s.split(":");
			System.out.println("FROM : " + elements[0]);
			System.out.println("Date : " + elements[2]);
			System.out.println("SUBJECT : " + elements[3]);
			System.out.println("\n----------------------------\n");
			System.out.println(elements[4]);
			System.out.println("\n----------------------------\n");
			i++;
		}
	}

}
