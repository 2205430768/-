package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import bean.GMessage;
import bean.PMessage;
import bean.PackGMessage;
import bean.PackPMessage;
import bean.User;
import service.MessageService;
import service.RoomService;

public class GetMessage extends HttpServlet {
    MessageService messageService=new MessageService();
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
                	 
                	 String userID=request.getParameter("userID");
                	 String msgtype=request.getParameter("msgtype");
                	 json.addProperty("userID", userID);
                	 json.addProperty("msgtype", msgtype);
                	 if(msgtype.equals("1")){
                		 List<PackPMessage> pmessage=messageService.getPrivateMessage(Integer.valueOf(userID));
                  	     List<PackPMessage> privateMessages=new ArrayList<PackPMessage>();
                  	     List<PackPMessage> validateMessages=new ArrayList<PackPMessage>();
                  	     for(PackPMessage pp:pmessage){
                  	    	 if(pp.getMessages().get(0).getType().equals("3")){
                  	    		validateMessages.add(pp);
                  	    	 }
                  	    	 else{
                  	    		privateMessages.add(pp);
                  	    	 }
                  	     }
                  	     List<PackGMessage> gmessage=messageService.getGroupMessage(Integer.valueOf(userID));
                  	     json.addProperty("private", gson.toJson(privateMessages));
           		         json.addProperty("validate", gson.toJson(validateMessages));
           		         json.addProperty("group", gson.toJson(gmessage));
           		         json.addProperty("code", "1");
          		         json.addProperty("msg", "获取所有消息成功"); 
                	 }
                	 else if(msgtype.equals("2")){
                		 List<PackPMessage> validateMessages=messageService.getValidateMessage(Integer.valueOf(userID));
                		 json.addProperty("validate", gson.toJson(validateMessages));
                		 json.addProperty("code", "0");
             		     json.addProperty("msg", "获取所有验证消息成功"); 
                	 }
                	 else{
                		 json.addProperty("code", "0");
             		     json.addProperty("msg", "msgtype错误"); 
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
