package dao;

public class RelationDao extends BaseDao{

	public boolean theyAreFriend(int userID,int friendID){
		String sql = "select * from relationship where userID=? and friendID=?";
		Object[] paramsValue = {new Integer(userID),new Integer(friendID)};
		return super.queryUserExist(sql, paramsValue);
	}
	
}
