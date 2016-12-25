package entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FinalUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="NAME")
	private String userName;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="RIGHTS_ID")
	private NewsGroupRight newsRights;
	
	public FinalUser(){}
	public FinalUser(String userName) {
		this.userName = userName;
		this.newsRights = new NewsGroupRight(true,true);
	}
	
	
	/*Getters + Setters*/
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public NewsGroupRight getNewsRights() {
		return newsRights;
	}

	public void setNewsRights(NewsGroupRight newsRights) {
		this.newsRights = newsRights;
	}
	
	/*Override Methods*/
	@Override
	public String toString() {
		return "Name : " + userName;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof FinalUser))return false;
	    FinalUser user = (FinalUser)obj;
	    return (user.getId() == this.getId());
	}
	
	
	
	
}
