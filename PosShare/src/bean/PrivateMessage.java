package bean;

public class PrivateMessage {
	private int messageID;
	private String content;
	private int sendID;
	private int receiveID;
	private String sendTime;
	private String status;
	public PrivateMessage(){}
	public PrivateMessage(int messageID, String content, int sendID, int receiveID, String sendTime, String status) {
		super();
		this.messageID = messageID;
		this.content = content;
		this.sendID = sendID;
		this.receiveID = receiveID;
		this.sendTime = sendTime;
		this.status = status;
	}
	public int getMessageID() {
		return messageID;
	}
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSendID() {
		return sendID;
	}
	public void setSendID(int sendID) {
		this.sendID = sendID;
	}
	public int getReceiveID() {
		return receiveID;
	}
	public void setReceiveID(int receiveID) {
		this.receiveID = receiveID;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
