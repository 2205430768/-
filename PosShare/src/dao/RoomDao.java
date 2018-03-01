package dao;

import java.util.ArrayList;
import java.util.List;

import bean.Room;
import bean.RoomUser;
import bean.User;
import bean.User2User;
import utils.Base64Coder;

public class RoomDao extends BaseDao{

	public int createRoom(String createrID,String createTime,String position,
			String endTime,String type,String name,String roomImageUrl){
		int id=findUsedRoomID();
		String sql = "insert into room (id,createrID,createTime,position,endTime,type,name,roomImage) values"
		 		+ " (?,?,?,?,?,?,?,?)";
		 Object[] paramsValue = {new Integer(id),createrID,createTime,position,endTime,type,name,roomImageUrl};
		 super.update(sql, paramsValue);
		 return id;
	}
	public int findUsedRoomID(){
		  int n=20;
		  boolean flag;
		  while(n-->0){
			  int id=(int) (Math.random()*10000);
			  String sql = "select * from room where id=?";
			  Object[] paramsValue = {new Integer(id)};
			  flag=super.queryUserExist(sql, paramsValue);
			  if(flag==false){
				  return id;
			  }
		  }
		  for(int id=0;id<10000;id++){
			  String sql = "select * from room where id=?";
			  Object[] paramsValue = {new Integer(id)};
			  flag=super.queryUserExist(sql, paramsValue);
			  if(flag==false){
				  return id;
			  }
			  
		  }
		  return -1;
		  
		
	}
	
	public void agreeUser2Room(int roomID,int userID){
		String sql = "insert into room_user (roomID,userID,position,speed,tool,remainTime) values"
		 		+ " (?,?,?,?,?,?)";
		 Object[] paramsValue = {new Integer(roomID),new Integer(userID)
				 ,"unknown","unknown","unknown","unknown"};
		 super.update(sql, paramsValue);
		
	}
	public RoomUser getRoomUser(int roomID,int userID){
		String sql = "select * from room_user where roomID=? and userID=?";
		Object[] paramsValue = {new Integer(roomID),new Integer(userID)};
		List<RoomUser>  roomUsers=super.query(sql, paramsValue,RoomUser.class);
		if(roomUsers!=null&&roomUsers.size()>0){
			return roomUsers.get(0);
		}
		return null;
	}
	public List<RoomUser> getAllRoomUser(int roomID){
		String sql = "select * from room_user where roomID=?";
		Object[] paramsValue = {new Integer(roomID)};
		List<RoomUser>  roomUsers=super.query(sql, paramsValue,RoomUser.class);
		return roomUsers;
	}
	public void deleteRoomUser(int roomID,int userID){
		String sql = "delete from room_user where roomID=? and userID=?";
		 Object[] paramsValue = {new Integer(roomID),new Integer(userID)};
		 super.update(sql, paramsValue);
		
	}
	public Room getRoom(int roomID){
		String sql = "select * from room where id=?";
		Object[] paramsValue = {new Integer(roomID)};
		List<Room>  rooms=super.query(sql, paramsValue,Room.class);
		if(rooms!=null&&rooms.size()>0){
			return rooms.get(0);
		}
		return null;
	}
	public User findUserByUserID(int friendID){
		String sql = "select * from user where id=?";
		Object[] paramsValue = {new Integer(friendID)};
		List<User>  users=super.query(sql, paramsValue,User.class);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}
	public List<User> getRoomUsers(int roomID){
		String sql = "select * from room_user where roomID=?";
		Object[] paramsValue = {new Integer(roomID)};
		List<RoomUser>  rusers=super.query(sql, paramsValue,RoomUser.class);
		List<User> results=new ArrayList<User>();
		for(RoomUser ru:rusers){
			results.add(findUserByUserID(ru.getUserID()));
		}
		return results;
	}
	public List<Room> getRooms(int userID){
		String sql = "select * from room_user where userID=?";
		Object[] paramsValue = {new Integer(userID)};
		List<RoomUser>  rusers=super.query(sql, paramsValue,RoomUser.class);
		List<Room> results=new ArrayList<Room>();
		for(RoomUser ru:rusers){
			results.add(getRoom(ru.getRoomID()));
		}
		return results;
		
	}
	public Room getRoomByRoomID(int roomID){
		String sql = "select * from room where id=?";
		Object[] paramsValue = {new Integer(roomID)};
		List<Room> rooms=super.query(sql, paramsValue,Room.class);
		if(rooms!=null&&rooms.size()>0){
			return rooms.get(0);
		}
		return null;
	}
	public void deleteRoomUser(int roomID){
		String sql = "select * from room_user where roomID=?";
		Object[] paramsValue = {new Integer(roomID)};
		List<RoomUser> roomusers=super.query(sql, paramsValue,RoomUser.class);
		MessageDao messageDao=new MessageDao();
		String timeStr=String.valueOf(System.currentTimeMillis());
		for(RoomUser ru:roomusers){
			messageDao.addGroupMessage("4", "该群已经被解散",roomID,
					-1, ru.getUserID(), timeStr, "1","","");
		}
		sql = "delete from room_user where roomID=?";
		super.update(sql, paramsValue);
		
		
	}
	public void updatePosition(int roomID,int userID,String position,String speed,String tool,String remainTime){
		String sql = "update room_user set position=?,speed=?,tool=?,remainTime=? where userID=?"
				+" and roomID=?";
		Object[] paramsValue = {position,speed,tool,remainTime,new Integer(userID),new Integer(roomID)};
		super.update(sql, paramsValue);
	}
	public boolean deleteRoom(){
		long time=System.currentTimeMillis();
		String sql = "select * from room where endTime<?";
		Object[] paramsValue = {new Long(time)};
		List<Room> rooms=super.query(sql, paramsValue,Room.class);
		if(rooms!=null&&rooms.size()>0){
			  for(Room room:rooms){
				  Base64Coder.removeRoomImage(room.getCreaterID());
				  deleteRoomUser(room.getId());
				  sql = "delete from room where id=?";
				  Object[] paramsValue1 = {new Integer(room.getId())};
				  super.update(sql, paramsValue1);
			  }
			  return true;
		}
		return false;
	}
	
	
}
