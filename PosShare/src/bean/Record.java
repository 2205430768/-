package bean;

public class Record {
   private int recordID;
   private int userID;
   private long createTime;
   private long endTime;
   private String endPosition;
   private String type;
   private String positions;
   private String name;
   public Record(){
	   
   }
public int getRecordID() {
	return recordID;
}
public void setRecordID(int recordID) {
	this.recordID = recordID;
}
public int getUserID() {
	return userID;
}
public void setUserID(int userID) {
	this.userID = userID;
}
public long getCreateTime() {
	return createTime;
}
public void setCreateTime(long createTime) {
	this.createTime = createTime;
}
public long getEndTime() {
	return endTime;
}
public void setEndTime(long endTime) {
	this.endTime = endTime;
}
public String getEndPosition() {
	return endPosition;
}
public void setEndPosition(String endPosition) {
	this.endPosition = endPosition;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getPositions() {
	return positions;
}
public void setPositions(String positions) {
	this.positions = positions;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
    
   
   
}
