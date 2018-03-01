package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import service.RoomService;
import utils.Base64Coder;

public class CreateRoom extends HttpServlet {
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
              try {
            	 String createrID=request.getParameter("createrID");
            	 String createTime=request.getParameter("createTime");
            	 String position=request.getParameter("position");
            	 String endTime=request.getParameter("endTime");
            	 String type=request.getParameter("type");
            	 String name=request.getParameter("name");
            	 String roomImage=request.getParameter("roomImage");
            	 String roomImageUrl=null;
     			 if(roomImage!=null&&(!roomImage.equals(""))){
     				roomImageUrl=Base64Coder.getRoomImageURL(createrID,request.getSession().getServletContext().getRealPath(""),roomImage);
     		     }
            	 int id=roomService.createRoom(createrID, createTime, position, endTime, type,name,roomImageUrl);
            	 if(id!=-1){
            		 json.addProperty("code", "1");
     	    		 json.addProperty("msg", "创建成功");
     	    		 json.addProperty("room_id", id+"");
            	 }
            	 else{
            		 json.addProperty("code", "0");
     	    		 json.addProperty("msg", "创建失败");
            	 }
            	 
            	 
			} catch (Exception e) {
				e.printStackTrace();
				json.addProperty("code", "0");
	    		json.addProperty("msg", "创建失败");
			}   
 		     response.setCharacterEncoding("UTF-8");  
 			 PrintWriter out = response.getWriter(); 
 			 
 			 System.out.println(json.toString());
 		     out.write(json.toString());  
 		     out.flush();  
 		     out.close();     
		
		
	}

}
