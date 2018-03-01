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
import bean.User;
import service.RoomService;
import service.UserService;
import utils.Constant;

public class GetFriendAndGroup extends HttpServlet {
    UserService userService=new UserService();
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
        	 if(Constant.ImageDirectory==null){
        		 Constant.ImageDirectory=request.getSession().getServletContext().getRealPath("");
        	 }
        	 //boolean flag=roomService.deleteRoom();
        	
      	     String userID=request.getParameter("userID");
      	     List<User> users=userService.getFriends(Integer.valueOf(userID));
      	     List<Room> rooms=roomService.getRooms(Integer.valueOf(userID));
      	     json.addProperty("users", gson.toJson(users));
      	     json.addProperty("rooms", gson.toJson(rooms));
      	     json.addProperty("code", "1");
    	     json.addProperty("msg", "获取成功");
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
