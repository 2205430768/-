package service;

import java.util.List;

import bean.Record;
import dao.RecordDao;

public class RecordService {
	RecordDao recordDao=new RecordDao();
	public void addRecord(int userID,int createTime,int endTime,String endPosition,
			String type,String positions,String name){
		recordDao.addRecord(userID,createTime, endTime, endPosition, type, positions, name);
	}
	public List<Record> getRecordsByUserID(int userID){
		 return recordDao.getRecordsByUserID(userID);
	}
}
