package bean;

import java.util.List;

public class PackPMessage {
	private List<PMessage> messages;
	private User sendUser;
	public PackPMessage(){}
	public PackPMessage(List<PMessage> messages, User sendUser) {
		this.messages = messages;
		this.sendUser = sendUser;
	}
	public List<PMessage> getMessages() {
		return messages;
	}
	public void setMessages(List<PMessage> messages) {
		this.messages = messages;
	}
	public User getSendUser() {
		return sendUser;
	}
	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}
	
	

}
