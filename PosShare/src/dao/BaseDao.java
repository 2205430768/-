package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import utils.JDBCUtils;

public class BaseDao {

	// 初始化参数
		private Connection con;
		private PreparedStatement pstmt;
		private ResultSet rs;

		public void update(String sql,Object[] paramsValue){
			
			try {
				con=JDBCUtils.getConnection();
				pstmt=con.prepareStatement(sql);
				int count=pstmt.getParameterMetaData().getParameterCount();
				if (paramsValue != null && paramsValue.length > 0) {
					
					for(int i=0;i<count;i++) {
						pstmt.setObject(i+1, paramsValue[i]);
					}
					
				}
				
				// 执行更新
				pstmt.executeUpdate();
				
				
				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			finally{
				JDBCUtils.closeAll(con, pstmt, rs);
			}
			
			
		}
		
		public <T> List<T> query(String sql, Object[] paramsValue,Class<T> clazz){
			try {
				List<T> list = new ArrayList<T>();
				T t=null;
				con=JDBCUtils.getConnection();
				
				pstmt=con.prepareStatement(sql);
				if(paramsValue!=null&&paramsValue.length>0){
					for(int i=1;i<=paramsValue.length;++i){
						pstmt.setObject(i,paramsValue[i-1]);
					}
				}
				rs = pstmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				// ---> 获取列的个数
				int columnCount = rsmd.getColumnCount();
				while(rs.next()){
					t=clazz.newInstance();
					for (int i=0; i<columnCount; i++) {
						String columnName = rsmd.getColumnName(i + 1);
						// 获取每一列的列名称, 对应的值
						Object value = rs.getObject(columnName);
						// 封装： 设置到t对象的属性中  【BeanUtils组件】
						BeanUtils.copyProperty(t, columnName, value);
						
					}
					list.add(t);
				}
				return list;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			finally{
				JDBCUtils.closeAll(con, pstmt, rs);
			}
			
			
			
			
		}
		
		public boolean queryUserExist(String sql, Object[] paramsValue){
			try {
				con=JDBCUtils.getConnection();
				
				pstmt=con.prepareStatement(sql);
				if(paramsValue!=null&&paramsValue.length>0){
					for(int i=1;i<=paramsValue.length;++i){
						pstmt.setObject(i,paramsValue[i-1]);
					}
				}
				rs = pstmt.executeQuery();
				
				return rs.next();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			finally{
				JDBCUtils.closeAll(con, pstmt, rs);
			}
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
