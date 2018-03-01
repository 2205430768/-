package dao;

import java.util.List;

import bean.PMessage;
import bean.Record;

public class RecordDao extends BaseDao{
	public void addRecord(int userID,int createTime,int endTime,String endPosition,
			String type,String positions,String name){
		String sql = "insert into record (userID,createTime,endTime,endPosition,type,"
				+"positions,name) values"+ " (?,?,?,?,?,?,?)";
		Object[] paramsValue = {userID,createTime,endTime,endPosition,type,positions,name};
		super.update(sql, paramsValue);
	}
	public List<Record> getRecordsByUserID(int userID){
		 String sql = "select * from record where userID=? order by createTime";
		 Object[] paramsValue = {new Integer(userID)};
		 List<Record>  records=super.query(sql, paramsValue,Record.class);
		 return records;
	}
	
}
