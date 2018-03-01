package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtils {
	private static String url="jdbc:mysql:///easy_together?useUnicode=true&characterEncoding=utf8";
	private static String user="root";
	private static String password="root";
	
	public static Connection getConnection(){
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	public static void closeAll(Connection con, Statement stmt, ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(stmt!=null){
				stmt.close();
				stmt=null;
			}
			if(con!=null&&!con.isClosed()){
				con.close();
				con=null;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
