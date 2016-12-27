package entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="box_type")
public class Box implements IBox{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}


	@Column(name="name")
	public String boxName;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy="box")
	public List<Message> messages;
	
	@Column(name="box_type")
	public String Box_type;
	
	public Box(){}
	public Box(String boxName) {
		this.boxName = boxName;
		messages = new ArrayList<Message>();
	}


	@Override
	public List<Message> readAllMessages() {
		return messages;
	}

	@Override
	public void readAMessage(Message mess) {
		for(Message m : messages)
		{
			if(m.equals(mess))
			{
				System.out.println(m);
				break;
			}
		}
		
	}

	@Override
	public void addMessage(Message mess) {
		messages.add(mess);
		
	}


	public String getBoxName() {
		return boxName;
	}


	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}


	public Collection<Message> getMessages() {
		return messages;
	}


	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}


	public String getBox_type() {
		return Box_type;
	}


	public void setBox_type(String box_type) {
		Box_type = box_type;
	}
	
}
