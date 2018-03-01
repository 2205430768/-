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

import bean.User;
import dao.RoomDao;
import service.RoomService;
import service.UserService;

public class AddUser2Room extends HttpServlet {
	RoomService roomService=new RoomService();
	UserService userService=new UserService();
	
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
		Gson gson=new Gson();
        try {
      	 
         String roomID=request.getParameter("roomID");
      	 String op=request.getParameter("op");
      	 if(op.equals("find")){
      		String userID=request.getParameter("userID");
      		List<User> users=userService.getFriendsNotInGroup(Integer.valueOf(userID),Integer.valueOf(roomID));
      		json.addProperty("users", gson.toJson(users));
      		json.addProperty("msg", "获取成功");
      	 }
      	 else if(op.equals("add")){
      		String userIDs=request.getParameter("userIDs");
      		String[] ids=userIDs.split(",");
      		String userID=request.getParameter("userID");
      		RoomDao roomDao=new RoomDao();
      		int roomIDint=Integer.valueOf(roomID);
      		json.addProperty("msg", "添加成功");
      		json.addProperty("code", "1");
      		int myid=Integer.valueOf(userID);
      		for(String id:ids){
      			int idInt=Integer.valueOf(id);
      			if(roomDao.getRoomUser(roomIDint,idInt)==null){
      				roomService.addUser2Room(roomIDint,idInt,myid);
      			}
      			else{
      				json.addProperty("code", "0");
      	  		    json.addProperty("msg", "添加失败");
      			}
      			
      		}
      		
      	 }
      	else if(op.equals("agree")){
      		
      		String userIDs=request.getParameter("userIDs");
      		String[] ids=userIDs.split(",");
      		RoomDao roomDao=new RoomDao();
      		json.addProperty("msg", "同意成功");
      		 json.addProperty("code", "1");
      		int roomIDint=Integer.valueOf(roomID);
      		for(String id:ids){
      			int idInt=Integer.valueOf(id);
      			if(roomDao.getRoomUser(roomIDint,idInt)==null){
      				roomService.agreeUser2Room(roomIDint,idInt);
      			}
      			else{
      				json.addProperty("code", "0");
      	  		    json.addProperty("msg", "同意失败");
      			}
      			
      		}
      		
      		
      	 }
      	
	     
      	
      	 
		} catch (Exception e) {
			e.printStackTrace();
			json.addProperty("code", "0");
  		    json.addProperty("msg", "添加失败");
		}   
	     response.setCharacterEncoding("UTF-8");  
		 PrintWriter out = response.getWriter(); 
		 
		 System.out.println(json.toString());
	     out.write(json.toString());  
	     out.flush();  
	     out.close(); 
		
	}

}
