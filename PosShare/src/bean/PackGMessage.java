package bean;

import java.util.List;

public class PackGMessage {
	private List<GMessage> messages;
	private Room room;
	
	public PackGMessage(){}
	public PackGMessage(List<GMessage> messages, Room room) {
		
		this.messages = messages;
		this.room = room;
		
	}
	public List<GMessage> getMessages() {
		return messages;
	}
	public void setMessages(List<GMessage> messages) {
		this.messages = messages;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}

	
	
}
