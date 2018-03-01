package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.farsunset.cim.push.DefaultMessagePusher;
import com.farsunset.cim.sdk.server.model.Message;
import com.farsunset.cim.util.ContextHolder;
import com.farsunset.cim.util.MessageDispatcher;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import bean.PMessage;
import bean.User;
import service.MessageService;
import service.UserService;
import utils.Base64Coder;
import utils.Constant;

public class SendPrivateMessage extends HttpServlet {
     MessageService messageService=new MessageService();
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
		Gson gson = new Gson();  
		DefaultMessagePusher dmp=((DefaultMessagePusher)ContextHolder.getBean("messagePusher"));
		if(dmp==null){
			json.addProperty("dmp", "为空");
		}
		else{
			json.addProperty("dmp", "不为空");
		}
        try {
        	
      	 String type=request.getParameter("type");
      	 String content=request.getParameter("content");
      	 String sendID=request.getParameter("sendID");
      	 String receiveID=request.getParameter("receiveID");
      	 String sendTime=request.getParameter("sendTime");
      	 String status=request.getParameter("status");
      	 
      	 if(type.equals(Constant.IMAGE)){
       		content=Base64Coder.getMessageImageURL(request.getSession().getServletContext().getRealPath(""),content);
       		json.addProperty("content", content);
       	 }
      	int sendID_int=Integer.valueOf(sendID);
    	int receiveID_int=Integer.valueOf(receiveID);
      	 /*json.addProperty("type", type);
      	 json.addProperty("sendID", sendID);
      	 json.addProperty("receiveID", receiveID);
      	 json.addProperty("sendTime", sendTime);
      	 json.addProperty("status", status);*/
      	 if(((DefaultMessagePusher)ContextHolder.getBean("messagePusher")).isOnLine(receiveID)){
      		
         	 PMessage pmsg=new PMessage(1, type, content, sendID_int, receiveID_int, sendTime, status);
             User user=userService.findUserByUserID(sendID_int);
         	 Message message=new Message();
   		     message.setContent(gson.toJson(pmsg));
   		     gson.fromJson(gson.toJson(pmsg), PMessage.class);
   		     message.setAction("1");
   		     message.setSender(gson.toJson(user));
   		     message.setReceiver(receiveID);
             if(type.equals(Constant.IMAGE)){
            	 message.setTitle("图片");
             }
             else{
            	 message.setTitle(content);
             }
   		     
   		     MessageDispatcher.execute(message, "127.0.0.1");
      	 }
      	 else{
      		messageService.addPrivateMessage(type, content, sendID_int,
          			receiveID_int, sendTime, status);
      	 }
      	  json.addProperty("code", "1");
		  json.addProperty("msg", "发送成功");
		} catch (Exception e) {
			e.printStackTrace();
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
