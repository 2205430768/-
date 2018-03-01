package service;

import java.util.ArrayList;
import java.util.List;

import com.farsunset.cim.push.DefaultMessagePusher;
import com.farsunset.cim.sdk.server.model.Message;
import com.farsunset.cim.util.ContextHolder;
import com.farsunset.cim.util.MessageDispatcher;
import com.google.gson.Gson;

import bean.PMessage;
import bean.Room;
import bean.RoomUser;
import bean.User;
import dao.MessageDao;
import dao.RelationDao;
import dao.RoomDao;
import dao.UserDao;
import utils.Constant;

public class RoomService {
	RoomDao roomDao=new RoomDao();
	public int createRoom(String createrID,String createTime,String position,
			String endTime,String type,String name,String roomImageUrl){
		int roomID=roomDao.createRoom(createrID, createTime, position, endTime, type,name,roomImageUrl);
		if(roomID!=-1){
			roomDao.agreeUser2Room(roomID, Integer.valueOf(createrID));
		}
		return roomID;
	}
	public void addUser2Room(int roomID,int userID,int inviteID){
		MessageDao messgaeDao=new MessageDao();
		UserDao userDao=new UserDao();
	
		User inviteUser=userDao.findUserByUserID(inviteID);
		RoomDao roomDao=new RoomDao();
		Room room=roomDao.getRoom(roomID);
		if(inviteUser==null){
			System.out.println(inviteID+"该用户不存在");
			return;
		}
		if(messgaeDao.messageExist(inviteID, userID)){
			messgaeDao.updateAddGroupMessageToNew(inviteID, userID);
		}
		else{
			 String receiveStr=String.valueOf(userID);
			 if(((DefaultMessagePusher)ContextHolder.getBean("messagePusher")).isOnLine(receiveStr)){
				 Gson gson = new Gson();
				 String timeStr=Constant.returnTime();
             	 PMessage pmsg=new PMessage(1, "4", String.valueOf(roomID)+"#"+inviteUser.getName()+"邀请您加入"+room.getName(),roomID, userID, timeStr, "1");
                 
             	 Message message=new Message();
       		     message.setContent(gson.toJson(pmsg));
       		     message.setAction("3");
       		     message.setSender(gson.toJson(inviteUser));
       		     message.setReceiver(receiveStr);
       		     message.setTitle(inviteUser.getName()+"邀请您加入"+room.getName());
       		     MessageDispatcher.execute(message, "127.0.0.1");
          	 }
    		 else{
    			 //System.out.println("添加一条消息");
    			 messgaeDao.addRoomValidateMessage(inviteID,userID,String.valueOf(roomID)+"#"+inviteUser.getName()+"邀请您加入"+room.getName());
    		 }
			
		}
	}
	public void agreeUser2Room(int roomID,int userID){
		roomDao.agreeUser2Room(roomID, userID);
	}
	public List<Room> getRooms(int userID){
		
		return roomDao.getRooms(userID);
		
	}
	public Room getRoomByRoomID(int roomID){
		return roomDao.getRoomByRoomID(roomID);
	}
	public void deleteRoomUser(int roomID,int userID){
		roomDao.deleteRoomUser(roomID, userID);
		
	}
	public List<User> getRoomUsers(int roomID){
		
		return roomDao.getRoomUsers(roomID);
	}
	public boolean deleteRoom(){
		
		return roomDao.deleteRoom();
	}
	public RoomUser getRoomUser(int roomID,int userID){
		return roomDao.getRoomUser(roomID, userID);
	}
	public List<RoomUser> getAllRoomUser(int roomID){
		return roomDao.getAllRoomUser(roomID);
	}
	public void updatePosition(String roomIDs,int userID,String position,String speed,String tool,String remainTimes){
		String[] ids=roomIDs.split(",");
		String[] rts=remainTimes.split(",");
		for(int i=0;i<ids.length;i++){
			roomDao.updatePosition(Integer.valueOf(ids[i]),userID, position, speed, tool, rts[i]);
		}
		
		
	}
}
