package entity;

import java.util.List;

import entity.Message;
public interface IBox {

	public List<Message> readAllMessages();
	public void readAMessage(Message mess);
	public void addMessage(Message mess);
}
