package dao;

import java.util.List;

import bean.GMessage;
import bean.PMessage;
import utils.Constant;

public class MessageDao extends BaseDao{

	public void addValidateMessage(Integer sendID,Integer receiveID){
		String sql = "insert into private_message (type,content,sendID,receiveID,"
				+"sendTime,status) values"+ " (?,?,?,?,?,?)";
		
		String timeStr=Constant.returnTime();
		String str="«Î«ÛÃÌº”ƒ˙Œ™∫√”—";
	
		Object[] paramsValue = {"3",str,sendID,receiveID,timeStr,"1"};
		super.update(sql, paramsValue);
	}
	public void addRoomValidateMessage(Integer roomID,Integer receiveID,String content){
		String sql = "insert into private_message (type,content,sendID,receiveID,"
				+"sendTime,status) values"+ " (?,?,?,?,?,?)";
		
		String timeStr=Constant.returnTime();
	
		Object[] paramsValue = {"4",content,roomID,receiveID,timeStr,"1"};
		super.update(sql, paramsValue);
	}
	public boolean messageExist(Integer sendID,Integer receiveID){
		String sql = "select *from private_message where sendID=? and receiveID=? and status<>?";
		Object[] paramsValue = {sendID,receiveID,"2"};
		List<PMessage> pms=super.query(sql, paramsValue,PMessage.class);
		if(pms!=null&&pms.size()>0){
			return true;
		}
		return false;
	}
	public void addPrivateMessage(String type,String content,int sendID,int receiveID
			,String sendTime,String status){
		String sql = "insert into private_message (type,content,sendID,receiveID,"
				+"sendTime,status) values"+ " (?,?,?,?,?,?)";
		Object[] paramsValue = {type,content,sendID,receiveID,sendTime,status};
		super.update(sql, paramsValue);
	}
	public void addGroupMessage(String type,String content,int roomID,int sendID,int receiveID,
			String sendTime,String status,String senderName,String senderHead){
		String sql = "insert into group_message (type,content,roomID,sendID,receiveID,"
				+"sendTime,status,senderName,senderHead) values"+ " (?,?,?,?,?,?,?,?,?)";
		Object[] paramsValue = {type,content,roomID,sendID,receiveID,sendTime,status,senderName
				,senderHead};
		super.update(sql, paramsValue);
	}
	public void agreeBeFriend(Integer userID,Integer friendID){
		String sql = "insert into relationship (userID,friendID,intimacy"+") values"+ " (?,?,?)";
		Object[] paramsValue = {userID,friendID,"≈Û”—"};
		super.update(sql, paramsValue);
		sql = "insert into relationship (userID,friendID,intimacy"+") values"+ " (?,?,?)";
		Object[] paramsValue1 = {friendID,userID,"≈Û”—"};
		super.update(sql, paramsValue1);
	}
	public void deleteFriend(Integer userID,Integer friendID){
		String sql = "delete from relationship where userID=? and friendID=?";
		Object[] paramsValue = {userID,friendID};
		super.update(sql, paramsValue);
		Object[] paramsValue1 = {friendID,userID};
		super.update(sql, paramsValue1);
	}
	public List<PMessage> getPrivateMessage(int receiveID){
		 String sql = "select * from private_message where receiveID=? and status=? order by sendID";
		 Object[] paramsValue = {new Integer(receiveID),"1"};
		 List<PMessage>  pmessages=super.query(sql, paramsValue,PMessage.class);
		 return pmessages;
	}
	public List<PMessage> getValidateMessage(int receiveID){
		 String sql = "select * from private_message where receiveID=? and type=? order by sendID";
		 Object[] paramsValue = {new Integer(receiveID),"3"};
		 List<PMessage>  pmessages=super.query(sql, paramsValue,PMessage.class);
		 return pmessages;
	}
	public void deletePrivateMessage(int receiveID){
		String sql="delete from private_message where receiveID=? and status=? and type<>3";
		Object[] paramsValue = {new Integer(receiveID),"1"};
		super.update(sql, paramsValue);
	}
	public void updateValidateMessgae(int receiveID){
		String sql="update private_message set status='0' where receiveID=? and status=? and type=3";
		Object[] paramsValue = {new Integer(receiveID),"1"};
		super.update(sql, paramsValue);
	}
	public void updateValidateMessageToNew(int sendID,int receiveID){
		String sql="update private_message set status='1' where sendID=? and receiveID=? and type='3'";
		Object[] paramsValue = {new Integer(sendID),new Integer(receiveID)};
		super.update(sql, paramsValue);
	}
	public void updateAddGroupMessageToNew(int sendID,int receiveID){
		String sql="update private_message set status='1' where sendID=? and receiveID=? and type='4'";
		Object[] paramsValue = {new Integer(sendID),new Integer(receiveID)};
		super.update(sql, paramsValue);
	}
	public void processValidateMessgae(int receiveID,int sendID){
		//System.out.println(receiveID+":"+sendID);
		String sql="update private_message set status='2' where receiveID=? and sendID=? and status=? and type='3'";
		Object[] paramsValue = {new Integer(receiveID),new Integer(sendID),"0"};
		super.update(sql, paramsValue);
	}
	public List<GMessage> getGroupMessage(int receiveID){
		 String sql = "select * from group_message where receiveID=? and status=?";
		 Object[] paramsValue = {new Integer(receiveID),"1"};
		 List<GMessage>  gmessages=super.query(sql, paramsValue,GMessage.class);
		 
		 return gmessages;
	}
	public void deleteGroupMessage(int receiveID){
		String sql="delete from group_message where receiveID=? and status=?";
		Object[] paramsValue = {new Integer(receiveID),"1"};
		super.update(sql, paramsValue);
	}
	
	
	
	
	
	
	
	
	
	
}
