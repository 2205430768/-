package bean;

public class RoomUser {
	private int room_user_id;
	private int roomID;
	private int userID;
	private String position;
	private String speed;
	private String tool;
	private String remainTime;
	public RoomUser(){}
	public RoomUser(int room_user_id, int roomID, int userID) {
		super();
		this.room_user_id = room_user_id;
		this.roomID = roomID;
		this.userID = userID;
	}
	public int getRoom_user_id() {
		return room_user_id;
	}
	public void setRoom_user_id(int room_user_id) {
		this.room_user_id = room_user_id;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getTool() {
		return tool;
	}
	public void setTool(String tool) {
		this.tool = tool;
	}
	public String getRemainTime() {
		return remainTime;
	}
	public void setRemainTime(String remainTime) {
		this.remainTime = remainTime;
	}
	
	
	

}
