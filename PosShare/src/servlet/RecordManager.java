package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import bean.Record;
import service.RecordService;

public class RecordManager extends HttpServlet {
	RecordService recordService=new RecordService();
	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   doPost(request,response);
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObject json=new JsonObject();
		Gson gson = new Gson();  
        try {
         String op=request.getParameter("op");
         if(op.equals("add")){
        	 String userID=request.getParameter("userID");
          	 String createTime=request.getParameter("createTime");
          	 String endTime=request.getParameter("endTime");
          	 String endPosition=request.getParameter("endPosition");
          	 String type=request.getParameter("type");
          	 String positions=request.getParameter("positions");
          	 String name=request.getParameter("name");
          	 
             recordService.addRecord(Integer.valueOf(userID), 
            		 Integer.valueOf(createTime), Integer.valueOf(endTime), 
            		 endPosition, type, positions, name);
         	 json.addProperty("code", "1");
   		     json.addProperty("msg", "添加记录成功");
         }
         else if(op.equals("get")){
        	 String userID=request.getParameter("userID");
        	 List<Record> records=recordService.getRecordsByUserID(Integer.valueOf(userID));
        	 json.addProperty("code", "1");
        	 json.addProperty("record", gson.toJson(records));
   		     json.addProperty("msg", "获取记录成功");
         }
         else{
        	 json.addProperty("code", "0");
   		     json.addProperty("msg", "添加记录失败");
         }
		} catch (Exception e) {
			e.printStackTrace();
			json.addProperty("code", "0");
  		    json.addProperty("msg", "管理记录失败");
		}   
	     response.setCharacterEncoding("UTF-8");  
		 PrintWriter out = response.getWriter(); 
		 
		 System.out.println(json.toString());
	     out.write(json.toString());  
	     out.flush();  
	     out.close();     
		
	}

}
