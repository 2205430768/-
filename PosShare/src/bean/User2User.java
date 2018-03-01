package bean;

public class User2User {
	private int rID;
	private int userID;
	private int friendID;
	private String intimacy;
	public User2User(){}
	public User2User(int rID, int userID, int friendID, String intimacy) {
		this.rID = rID;
		this.userID = userID;
		this.friendID = friendID;
		this.intimacy = intimacy;
	}
	public int getrID() {
		return rID;
	}
	public void setrID(int rID) {
		this.rID = rID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getFriendID() {
		return friendID;
	}
	public void setFriendID(int friendID) {
		this.friendID = friendID;
	}
	public String getIntimacy() {
		return intimacy;
	}
	public void setIntimacy(String intimacy) {
		this.intimacy = intimacy;
	}
	
	

}
