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

import bean.Room;
import bean.RoomUser;
import bean.User;
import service.RoomService;
import service.UserService;

public class PositionGetAndUpdate extends HttpServlet {
	RoomService roomService=new RoomService();
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
        	 if(op.equals("get")){
        		 String count=request.getParameter("count");
        		 String roomID=request.getParameter("roomID");
        		 if(count.equals("one")){
        			  String userID=request.getParameter("userID");
        			  RoomUser rm=roomService.getRoomUser(Integer.valueOf(roomID),Integer.valueOf(userID));
        			  json.addProperty("one",gson.toJson(rm));
        			  json.addProperty("code", "0");
        	  		  json.addProperty("msg", "获取成功");
        		 }
        		 else if(count.equals("all")){
        			 List<RoomUser> roomUsers=roomService.getAllRoomUser(Integer.valueOf(roomID));
        			 json.addProperty("all",gson.toJson(roomUsers));
        			 json.addProperty("code", "0");
        	  		 json.addProperty("msg", "获取成功");
        		 }
        		 else{
        			 json.addProperty("code", "0");
        	  		 json.addProperty("msg", "获取失败");
        		 }
        	 }
        	 else if(op.equals("update")){
        		 String roomIds=request.getParameter("roomIds");
        		 String userID=request.getParameter("userID");
        		 String position=request.getParameter("position");
        		 String speed=request.getParameter("speed");
        		 String tool=request.getParameter("tool");
        		 String remainTimes=request.getParameter("remainTime");
        		 roomService.updatePosition(roomIds,Integer.valueOf(userID), 
        				 position, speed, tool, remainTimes);
        		 json.addProperty("code", "1");
        	     json.addProperty("msg", "更新成功");
        	 }
        	 else{
        		 json.addProperty("code", "0");
       		     json.addProperty("msg", "获取失败");
        	 }
		} catch (Exception e) {
			e.printStackTrace();
			json.addProperty("code", "0");
  		    json.addProperty("msg", "获取失败");
		}   
	     response.setCharacterEncoding("UTF-8");  
		 PrintWriter out = response.getWriter(); 
		 
		 System.out.println(json.toString());
	     out.write(json.toString());  
	     out.flush();  
	     out.close();     
	
		
	}

}
