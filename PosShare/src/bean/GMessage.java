package bean;

public class GMessage {
	private int gmessageID;
	private String content;
	private int roomID;
	private String status;
	private String sendTime;
	private int receiveID;
	private String type;
	private int sendID;
	private String senderName;
	private String senderHead;
	public GMessage(){}
	public GMessage(int gmessageID, String content, int roomID, String status, String sendTime, int receiveID,
			String type, int sendID, String senderName, String senderHead) {
		this.gmessageID = gmessageID;
		this.content = content;
		this.roomID = roomID;
		this.status = status;
		this.sendTime = sendTime;
		this.receiveID = receiveID;
		this.type = type;
		this.sendID = sendID;
		this.senderName = senderName;
		this.senderHead = senderHead;
	}
	public int getGmessageID() {
		return gmessageID;
	}
	public void setGmessageID(int gmessageID) {
		this.gmessageID = gmessageID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public int getReceiveID() {
		return receiveID;
	}
	public void setReceiveID(int receiveID) {
		this.receiveID = receiveID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSendID() {
		return sendID;
	}
	public void setSendID(int sendID) {
		this.sendID = sendID;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderHead() {
		return senderHead;
	}
	public void setSenderHead(String senderHead) {
		this.senderHead = senderHead;
	}
	
	
	

}
