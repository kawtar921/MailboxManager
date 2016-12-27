package client;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

import directoryInterface.ClientCommands;

public class ClientMenu {

	public static Scanner sc = new Scanner(System.in);
	public static ClientCommands directory;
	
	public static void menu()
	{
		System.out.println("1) Add a user");
		System.out.println("2) Remove a user");
		System.out.println("3) Display all users");
		System.out.println("4) Update a user's rights");
		System.out.println("5) Display a user's rights");
		System.out.println("6) Quit");
	}
	
	public static void scenario()
	{
		int command = -1;
		System.out.println("ADMINISTRATOR MENU");
		System.out.println("-----------------------");
		
		while(command!=6)
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
			directory = new ClientCommands();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		switch(command)
		{
		case 1: addUser(directory);break;
		case 2: removeUser(directory);break;
		case 3: displayAllUsers(directory);break;
		case 4: updateUserRight(directory);break;
		case 5: displayUserRight(directory);break;
		case 6: System.out.println("GoodBye !");break;
		default : System.out.println("Unkown Command !");
		}
	}
	
	public static void addUser(ClientCommands directory)
	{
		String username;
		System.out.println("Add User : ");
		System.out.println("Enter users' name : ");
		username = sc.nextLine();
		
		
		try {
			System.out.println(directory.userExists(username));
			if(!directory.userExists(username))
				System.out.println(directory.addUser(username));
			else
				System.out.println("This user already exists !");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void removeUser(ClientCommands directory)
	{
		String username;
		System.out.println("Remove User : ");
		System.out.println("Enter users' name : ");
		username = sc.nextLine();
		
		try {
			if(directory.userExists(username))
				System.out.println(directory.removeUser(username));
			else
				System.out.println("This user doesn't exist!");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void displayAllUsers(ClientCommands directory)
	{
		System.out.println("List of users : ");
		try {
			List<String> listUsers = directory.lookupAllUsers();
			int i = 1;
			for(String s : listUsers)
			{
				System.out.println("User " + i + " : " + s);
				i++;
			}
				
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void updateUserRight(ClientCommands directory)
	{
		String username;
		boolean read, write;
		
		try {
			System.out.println("Update User's rights : ");
			System.out.println("Enter users' name : ");
			username = sc.nextLine();
			if(directory.userExists(username))
			{
				System.out.println("Enter User's new rights : ");
				System.out.println("Reading rights : ");
				read = Boolean.parseBoolean(sc.nextLine());
				System.out.println("Writing rights : ");
				write = Boolean.parseBoolean(sc.nextLine());
				
				System.out.println(directory.updateAUserRights(username, read, write));
			}
			else
			{
				System.out.println("This user doesn't exist!");
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void displayUserRight(ClientCommands directory)
	{
		String username;
		System.out.println("Enter users' name : ");
		username = sc.nextLine();
		
		try {
			if(directory.userExists(username))
			{
				List<Boolean> listRights = directory.lookupAUserRights(username);
				System.out.println("Read : " + listRights.get(0) + " , Write : " + listRights.get(1));
			}
			else
				System.out.println("This user doesn't exist!");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		scenario();
	}

}
