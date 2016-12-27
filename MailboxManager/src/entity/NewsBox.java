package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue("NewsBox")
public class NewsBox extends Box{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ElementCollection
	@CollectionTable(name = "USERS")
	private List<String> users;

	public NewsBox(){}
	
	public NewsBox(String boxName) {
		super(boxName);
		users = new ArrayList<String>();
	}

	public void addUser(String user)
	{
		users.add(user);
	}
	
	public void removeUser(String user)
	{
		users.remove(user);
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
}
