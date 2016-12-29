package client;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.List;

import directoryInterface.ClientCommands;

public class ScenarioPredefini {

	public static void main(String[] args) {
		try {
			ClientCommands directory = new ClientCommands();
			
			//Step 1 : Add Users
			System.out.println("Step 1 : Adding users");
			System.out.println("----------------------------");
			System.out.println(directory.addUser("Alpha"));
			System.out.println(directory.addUser("Beta"));
			System.out.println(directory.addUser("Gamma"));
			
			//Step 2 : Remove user
			System.out.println("\n\nStep 2 : Remove user");
			System.out.println("----------------------------");
			System.out.println(directory.removeUser("Gamma"));
			
			//Step 3 : List of users
			System.out.println("\n\nStep 3 : List of users");
			System.out.println("----------------------------");
			List<String> listUsers = directory.lookupAllUsers();
			listUsers.forEach(s -> System.out.println(s));
			
			//Step 4 : Update users rights
			System.out.println("\n\nStep 4 : Update rights");
			System.out.println("----------------------------");
			System.out.println(directory.updateAUserRights("Alpha", false, true));
			System.out.println(directory.updateAUserRights("Beta", true, false));
			
			//Step 5 : Get rights
			System.out.println("\n\nStep 5 : Get users rights");
			System.out.println("----------------------------");
			List<Boolean> rights = directory.lookupAUserRights("Alpha");
			System.out.println("Alpha's rights are [Read : " + rights.get(0) + " , Write : " + rights.get(1) + "]");
			rights = directory.lookupAUserRights("Beta");
			System.out.println("Beta's rights are [Read : " + rights.get(0) + " , Write : " + rights.get(1) + "]");
			
			//Step 6 : Error cases
			System.out.println("\n\nStep 6 : Error Cases");
			System.out.println("----------------------------");
			System.out.println("Non-existing user : ");
			System.out.println("Gamma exists ? : " + directory.userExists("Gamma"));
			
		} catch (NotBoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
