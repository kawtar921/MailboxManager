package entity;

import java.util.List;

import entity.Message;

public interface IMailBox {
	
	public void deleteAMessage(String m);
	public void deleteReadMessages();
	public void deleteAllMessages();
	public List<Message> readNewMessages();

}
