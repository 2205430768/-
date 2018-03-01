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

public class GetRoomUsers extends HttpServlet {
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
      	     String roomID=request.getParameter("roomID");
      	     int id=Integer.valueOf(roomID);
      	     List<User> users=roomService.getRoomUsers(id);
      	     Room room=roomService.getRoomByRoomID(id);
      	     json.addProperty("code", "1");
    	     json.addProperty("msg", "获取成功");
    	     json.addProperty("room", gson.toJson(room));
    	     json.addProperty("users", gson.toJson(users));
		} catch (Exception e) {
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
