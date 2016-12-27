package client;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import mailboxInterface.ClientCommands;


public class ClientMenu {
	public static Scanner sc = new Scanner(System.in);
	public static ClientCommands mailbox;
	public static String username;
	
	public static void menu()
	{
		System.out.println("1) Send a message");
		System.out.println("2) Send news");
		System.out.println("3) Read new messages");
		System.out.println("4) Read all messages");
		System.out.println("5) Read news");
		System.out.println("6) Remove read messages");
		System.out.println("7) Remove a message");
		System.out.println("8) Quit");
		
	}
	
	public static void scenario()
	{
		int command = -1;
		System.out.println("MAILBOX MENU");
		System.out.println("-----------------------");
		System.out.println("\n Enter your username : ");
		username = sc.nextLine();
		
		while(command!=8)
		{
			menu();
			System.out.println("Choose a command : ");
			command = sc.nextInt();
			sc.nextLine();
			
			executeCommand(command);
		}
	}
	

	public static void executeCommand(int command)
	{
		try {
			
			mailbox = new ClientCommands();
			switch(command)
			{
			case 1: sendMessage(mailbox);break;
			case 2: sendNews(mailbox);break;
			case 3: readNewMessages(mailbox);break;
			case 4: readAllMessages(mailbox);break;
			case 5: readNews(mailbox);break;
			case 6: removeReadMessages(mailbox);break;
			case 7: removeMessage(mailbox);break;
			case 8: System.out.println("GoodBye !");break;
			default : System.out.println("Unkown Command !");
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void readNews(ClientCommands mailbox2) {
		try {
			List<String> liste = mailbox.readNews();
			if(liste.isEmpty())
			{
				System.out.println("No news !");
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
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	private static void sendNews(ClientCommands mailbox2) {
		String subject,body;
		String sendingDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		System.out.println("Enter subject : ");
		subject = sc.nextLine();
		System.out.println("Enter message : ");
		body = sc.nextLine();
		
		try {
			System.out.println(mailbox.sendNews(username, sendingDate, subject, body));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private static void removeReadMessages(ClientCommands mailbox) {
		char confirm;
		System.out.println("Confirm deletion of messages (y/n) : ");
		confirm = sc.nextLine().charAt(0);
		
		if(confirm=='y' || confirm=='Y')
		{
			try {
				System.out.println(mailbox.removeReadMessages(username));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
	}

	private static void readAllMessages(ClientCommands mailbox) {
		try {
			List<String> liste = mailbox.readAllMessages(username);
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
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	private static void readNewMessages(ClientCommands mailbox) {
		try {
			List<String> liste = mailbox.readNewMessages(username);
			if(liste.isEmpty())
			{
				System.out.println("No new messages !");
				return;
			}
			System.out.println("List of new messages : ");
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
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private static void removeMessage(ClientCommands mailbox) {
		String subject;
		char confirm;
		System.out.println("Enter message's subject : ");
		subject = sc.nextLine();
		
		System.out.println("Confirm deletion of messages (y/n) : ");
		confirm = sc.nextLine().charAt(0);
		if(confirm=='y' || confirm=='Y')
		{
			try {
				System.out.println(mailbox.removeMessage(username, subject));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	private static void sendMessage(ClientCommands mailbox) {
		String receiver,subject,body;
		String sendingDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		System.out.println("Enter receiver's name : ");
		receiver = sc.nextLine();
		System.out.println("Enter subject : ");
		subject = sc.nextLine();
		System.out.println("Enter message : ");
		body = sc.nextLine();
		
		try {
			System.out.println(mailbox.sendMessage(username, receiver, sendingDate, subject, body));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		scenario();
	}

}
