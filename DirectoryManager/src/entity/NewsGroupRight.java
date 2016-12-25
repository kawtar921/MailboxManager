package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsGroupRight implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="READ_RIGHT")
	private boolean readNewsGroup = true;
	
	@Column(name="WRITE_RIGHT")
	private boolean writeNewsGroup = true;

	public long getId() {
		return id;
	}

	public NewsGroupRight() {}
	public NewsGroupRight(boolean readNewsGroup, boolean writeNewsGroup) {
		this.readNewsGroup = readNewsGroup;
		this.writeNewsGroup = writeNewsGroup;
	}


	public boolean isReadNewsGroup() {
		return readNewsGroup;
	}

	public void setReadNewsGroup() {
		readNewsGroup = true;
	}

	public boolean isWriteNewsGroup() {
		return writeNewsGroup;
	}

	public void setWriteNewsGroup() {
		writeNewsGroup = true;
	}
	
	public void unsetReadAcess()
	{
		readNewsGroup = false;
	}
	
	public void unsetWriteAcess()
	{
		writeNewsGroup = false;
	}
	
	@Override
	public String toString() {
		return "Write : " + writeNewsGroup + " Read : " + readNewsGroup;
	}
	
	 
}
