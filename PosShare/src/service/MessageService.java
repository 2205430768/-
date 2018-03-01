package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.farsunset.cim.push.DefaultMessagePusher;
import com.farsunset.cim.sdk.server.model.Message;
import com.farsunset.cim.util.ContextHolder;
import com.farsunset.cim.util.MessageDispatcher;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import bean.GMessage;
import bean.PMessage;
import bean.PackGMessage;
import bean.PackPMessage;
import bean.Room;
import bean.User;
import dao.MessageDao;
import dao.RelationDao;
import dao.RoomDao;
import dao.UserDao;
import utils.Constant;

public class MessageService {
	MessageDao messgaeDao=new MessageDao();
	
	public boolean addValidateMessage(Integer sendID,Integer receiveID){
		RelationDao relationDao=new RelationDao();
		if(relationDao.theyAreFriend(sendID, receiveID)){
			return false;
		}
		if(messgaeDao.messageExist(sendID, receiveID)){
			messgaeDao.updateValidateMessageToNew(sendID, receiveID);
		}
		else{
			String receiveStr=String.valueOf(receiveID);
			 if(((DefaultMessagePusher)ContextHolder.getBean("messagePusher")).isOnLine(receiveStr)){
				 long currentTime=System.currentTimeMillis();
				 Gson gson = new Gson();
				 UserService userService=new UserService();
				 String timeStr=Constant.returnTime();
             	 PMessage pmsg=new PMessage(1, "3", "请求添加您为好友", sendID, receiveID, timeStr, "1");
                 User user=userService.findUserByUserID(sendID);
             	 Message message=new Message();
       		     message.setContent(gson.toJson(pmsg));
       		     message.setAction("3");
       		     message.setSender(gson.toJson(user));
       		     message.setReceiver(receiveStr);
       		     message.setTitle("请求添加您为好友");
       		     MessageDispatcher.execute(message, "127.0.0.1");
          	 }
    		 else{
    			 messgaeDao.addValidateMessage(sendID, receiveID);
    		 }
			
		}
		
		return true;
	}
	public void agreeBeFriend(Integer userID,Integer friendID){
		messgaeDao.agreeBeFriend(userID, friendID);
		messgaeDao.processValidateMessgae(userID, friendID);
	}
	public void deleteFriend(Integer userID,Integer friendID){
		messgaeDao.deleteFriend(userID, friendID);
	}
	
	public List<PackPMessage> getPrivateMessage(int receiveID){
		 List<PMessage> pmessages=messgaeDao.getPrivateMessage(receiveID);
		 messgaeDao.deletePrivateMessage(receiveID);
		 messgaeDao.updateValidateMessgae(receiveID);
		 List<PackPMessage> ppmsg=new ArrayList<PackPMessage>();
		 UserDao userDao=new UserDao();
		 int sendID=-1;
		 List<PMessage> pms=null;
		 User user=null;
		 for(PMessage pm:pmessages){
			if(pm.getSendID()!=sendID){
				if(pms!=null&&user!=null){
					ppmsg.add(new PackPMessage(pms, user));
				}
				sendID=pm.getSendID();
				user=userDao.findUserByUserID(sendID);
				pms=new ArrayList<PMessage>();
				pms.add(pm);
				
			}
			else{
				pms.add(pm);
			}
			
			
		 }
		 
		 if(pms!=null&&user!=null){
				ppmsg.add(new PackPMessage(pms, user));
		}
		 
		 return ppmsg;
	}
	public List<PackPMessage> getValidateMessage(int receiveID){
		 List<PMessage> pmessages=messgaeDao.getValidateMessage(receiveID);
		 messgaeDao.updateValidateMessgae(receiveID);
		 List<PackPMessage> ppmsg=new ArrayList<PackPMessage>();
		 UserDao userDao=new UserDao();
		 int sendID=-1;
		 List<PMessage> pms=null;
		 User user=null;
		 for(PMessage pm:pmessages){
			if(pm.getSendID()!=sendID){
				if(pms!=null&&user!=null){
					ppmsg.add(new PackPMessage(pms, user));
				}
				sendID=pm.getSendID();
				user=userDao.findUserByUserID(sendID);
				pms=new ArrayList<PMessage>();
				pms.add(pm);
				
			}
			else{
				pms.add(pm);
			}
			
			
		 }
		 
		 if(pms!=null&&user!=null){
				ppmsg.add(new PackPMessage(pms, user));
		}
		 
		 return ppmsg;
	}
	public List<PackGMessage> getGroupMessage(int receiveID){
		
		 List<GMessage>  gmessages=messgaeDao.getGroupMessage(receiveID);
		 List<PackGMessage> packgMessages=new ArrayList<PackGMessage>();
		 int roomID=-1;
		 Room room=null;
		 List<GMessage> gmsgs=null;
		 RoomDao roomDao=new RoomDao();
		 for(GMessage pcm:gmessages){
			   if(roomID!=pcm.getRoomID()){
				   if(room!=null&&gmsgs!=null){
		
					   packgMessages.add(new PackGMessage(gmsgs, room));
				   }
				   gmsgs=new ArrayList<GMessage>();
				   roomID=pcm.getRoomID();
				   room=roomDao.getRoom(roomID);
				   
			   }
			   else{
				   gmsgs.add(pcm);
			   }
		 }
		 if(room!=null&&gmsgs!=null){
			   packgMessages.add(new PackGMessage(gmsgs, room));
		  }
		 messgaeDao.deleteGroupMessage(receiveID);
		 return packgMessages;
	}
	public void addPrivateMessage(String type,String content,int sendID,int receiveID
			,String sendTime,String status){
		  messgaeDao.addPrivateMessage(type, content, sendID, receiveID, sendTime, status);
	}
	public void addGroupMessage(String type,String content,int roomID,int sendID,String sendTime,String status){
		RoomDao roomDao=new RoomDao();
		UserDao userDao=new UserDao();
		User sendUser=userDao.findUserByUserID(sendID);
		boolean isPicture=false;
		List<User> users=roomDao.getRoomUsers(roomID);
		Room room=roomDao.getRoom(roomID);
		Gson gson = new Gson();  
		String roomStr=gson.toJson(room);
		DefaultMessagePusher dmp=(DefaultMessagePusher)ContextHolder.getBean("messagePusher");
		if(type.equals("2")){
			isPicture=true;
		}
		for(User user:users){
			if(user.getId()!=sendID){
				String receiveID=String.valueOf(user.getId());
				if(dmp.isOnLine(receiveID)){
					 GMessage gmsg=new GMessage(1, content, roomID, status, sendTime, user.getId(),
							 type, sendID, sendUser.getName(), sendUser.getHead_url());
		             
		         	 Message message=new Message();
		   		     message.setContent(gson.toJson(gmsg));
		   		     message.setAction("2");
		   		     message.setSender(roomStr);
		   		     message.setReceiver(receiveID);
		   		     if(isPicture){
		   		    	message.setTitle("图片");
		   		     }
		   		     else{
		   		    	message.setTitle(content);
		   		     }
		   		     MessageDispatcher.execute(message, "127.0.0.1");
				}
				else{
					messgaeDao.addGroupMessage(type, content, roomID, sendID, user.getId(), sendTime, status,
							sendUser.getName(),sendUser.getHead_url());
				}
				
			}
		}
		
		
		
	}
}
