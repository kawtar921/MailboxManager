package ejb;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.FinalUser;
import entity.NewsGroupRight;

@Stateless(mappedName="ejb.IUserDirectory")
public class UserDirectory extends UnicastRemoteObject implements IUserDirectory{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Collection<FinalUser> listUsers;
	
	@PersistenceContext(unitName="pu1")
    private EntityManager em;
	
	public UserDirectory() throws RemoteException
	{
		listUsers = new ArrayList<FinalUser>();
	}
	
	
	/*Getters + Setters*/
	public Collection<FinalUser> getListUsers() {
		return listUsers;
	}

	public void setListUsers(Collection<FinalUser> listUsers) {
		this.listUsers = listUsers;
	}

	

	@Override
	public boolean userExists(String user) throws RemoteException {
		if(lookupUser(user)==null)
		   return false;
		
		return true;
	}

	
	@Override
	public String addUser(String username) {
		FinalUser user = new FinalUser(username);
		listUsers.add(user);
		
		//add user database
		em.persist(user);
		
		return "User " + username + " added!";
		
		
		//send to mailboxmanager to add user
		//client.addUser(user);
	}

	@Override
	public String removeUser(String username) {
		FinalUser user = lookupUser(username);
		listUsers.remove(user);
		
		//send to mailboxmanager to remove user
		//client.removeUser(user);
		
		//add user database
		em.remove(user.getNewsRights());
		em.remove(user);
		
		return "User " + username + " removed!";
	}

	@Override
	public List<String> lookupAllUsers() {
		Query query = em.createQuery("SELECT f.userName from FinalUser f");
		List<String> list = query.getResultList();
		return list;
			
	}

	@Override
	public List<Boolean> lookupAUserRights(String user) {
		List<Boolean> rights = new ArrayList<Boolean>();
		FinalUser f = lookupUser(user);
		
		rights.add(f.getNewsRights().isReadNewsGroup());
		rights.add(f.getNewsRights().isWriteNewsGroup());
		
		return rights;

	}

	@Override
	public String updateAUserRights(String user, boolean read, boolean write) {
		// TODO Auto-generated method stub
		lookupUser(user).setNewsRights(new NewsGroupRight(read,write));
		
		return "User " + user + " updated!";
	}
	
	private FinalUser lookupUser(String name)
	{ 
		Query q = em.createQuery("select f from FinalUser f where f.userName = :name");
        q.setParameter("name", name);
        return (FinalUser)q.getSingleResult();
		
	}


	
}
