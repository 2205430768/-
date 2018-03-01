package dao;

import java.util.ArrayList;
import java.util.List;

import bean.User;
import bean.User2User;

public class UserDao extends BaseDao{

	
	public void insertUser(User user){
		 String sql = "insert into user (username,password) values"
		 		+ " (?,?)";
		 Object[] paramsValue = {user.getUsername(),user.getPassword()};
		 super.update(sql, paramsValue);
	}
	public boolean hasUser(User user){
		String sql = "select * from user where username=?";
		Object[] paramsValue = {user.getUsername()};
		return super.queryUserExist(sql, paramsValue);
	}
	public User findUser(String username,String password){
		String sql = "select * from user where username=? and password=?";
		Object[] paramsValue = {username,password};
		List<User>  users=super.query(sql, paramsValue,User.class);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}
	public User findUserByUserName(String username){
		String sql = "select * from user where username=?";
		Object[] paramsValue = {username};
		List<User>  users=super.query(sql, paramsValue,User.class);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}
	public User findUserByUserName(int userID){
		String sql = "select * from user where id=?";
		Object[] paramsValue = {new Integer(userID)};
		List<User>  users=super.query(sql, paramsValue,User.class);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}
	public void updateUser(int userID,String name,String gender,String date,String headUrl){
		String sql = "update user set head_url=?,name=?,gender=?,date=? where id=?";
		Object[] paramsValue = {headUrl,name,gender,date,new Integer(userID)};
		super.update(sql, paramsValue);
	}
	public User findUserByUserID(int ID){
		String sql = "select * from user where id=?";
		Object[] paramsValue = {new Integer(ID)};
		List<User>  users=super.query(sql, paramsValue,User.class);
		if(users!=null&&users.size()>0){
			return users.get(0);
		}
		return null;
	}
	public List<User> getFriendsNotInGroup(int userID,int roomID){
		
		String sql = "select * from user where id in (select friendID from relationship where"
		  +" userID=? and friendID not in(select userID from room_user where roomID=?))";
		Object[] paramsValue = {new Integer(userID),new Integer(roomID)};
		List<User>  users=super.query(sql, paramsValue,User.class);
		return users;
	}
	
	public List<User> getFriends(int userID){
		String sql = "select * from relationship where userID=?";
		Object[] paramsValue = {new Integer(userID)};
		List<User2User>  users=super.query(sql, paramsValue,User2User.class);
		List<User> results=new ArrayList<User>();
		for(User2User u2u:users){
			results.add(findUserByUserID(u2u.getFriendID()));
		}
		return results;
	}
	      
	
}
