package service;

import java.util.ArrayList;
import java.util.List;

import bean.User;
import bean.User2User;
import dao.UserDao;

public class UserService {
	UserDao userDao=new UserDao();
	
	public void addUser(User user){
		userDao.insertUser(user);
	}
	public boolean hasUser(User user){
		return userDao.hasUser(user);
	}
	public User findUser(String username,String password){
		return userDao.findUser(username, password);
	}
	public User findUserByUserName(String username){
		return userDao.findUserByUserName(username);
	}
	public List<User> getFriends(int userID){
		
		return userDao.getFriends(userID);
	}
	public void updateUser(int userID,String name,String gender,String date,String headUrl){
		userDao.updateUser(userID, name, gender, date, headUrl);
	}
	public User findUserByUserID(int ID){
		
		return userDao.findUserByUserID(ID);
	}
    public List<User> getFriendsNotInGroup(int userID,int roomID){
		
		return userDao.getFriendsNotInGroup(userID,roomID);
	}
}
