package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import service.MessageService;
import service.RoomService;
import utils.Base64Coder;
import utils.Constant;

public class SendGroupMessage extends HttpServlet {
    MessageService messageService=new MessageService();
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
         //boolean flag=roomService.deleteRoom();
      	 String type=request.getParameter("type");
      	 String content=request.getParameter("content");
      	 int roomID=Integer.valueOf(request.getParameter("roomID"));
      	 int sendID=Integer.valueOf(request.getParameter("sendID"));
      	 String sendTime=request.getParameter("sendTime");
      	 String status=request.getParameter("status");
      	 if(type.equals(Constant.IMAGE)){
      		content=Base64Coder.getMessageImageURL(request.getSession().getServletContext().getRealPath(""),content);
      		json.addProperty("content", content);
      	 }
      	 messageService.addGroupMessage(type, content, roomID, sendID, sendTime, status);
      	 json.addProperty("code", "1");
		 json.addProperty("msg", "发送成功");
		} catch (Exception e) {
			json.addProperty("code", "0");
  		    json.addProperty("msg", "发送失败");
		}   
       
	     response.setCharacterEncoding("UTF-8");  
		 PrintWriter out = response.getWriter(); 
		 
		 System.out.println(json.toString());
	     out.write(json.toString());  
	     out.flush();  
	     out.close();  
		
	}

}
