package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import bean.User;
import dao.UserDao;
import service.MessageService;
import service.UserService;

public class Friend extends HttpServlet {
	UserService userService=new UserService();
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
             String userID=request.getParameter("userID");
             JsonObject json=new JsonObject();
             if(userID!=null){
            	 try {
            		 Integer id=Integer.valueOf(userID);
                     String op=request.getParameter("op");
        		     if(op.equals("search")){
        		    	 String username=request.getParameter("username");
        		    	 User user=userService.findUserByUserName(username);
        		    	 if(user!=null){
        		    		 Gson gson=new Gson();
        		    		 json.addProperty("code", "1");
        		    		 json.addProperty("msg", "�ɹ��ҵ�");
        		    		 json.addProperty("user", gson.toJson(user));
        		    	 }
        		    	 else{
        		    		 json.addProperty("code", "0");
        		    		 json.addProperty("msg", "û���ҵ�");
        		    	 }
        		    	 
        		     }
        		     else if(op.equals("add")){
        		    	 String friendID=request.getParameter("friendID");
        		    	 if(friendID!=null){
        		    		 Integer fid=Integer.valueOf(friendID);
        		    		 
        		    		 boolean flag=messageService.addValidateMessage(id, fid);
        		    		 if(flag){
        		    			 json.addProperty("code", "1");
            		    		 json.addProperty("msg", "���ͳɹ�");
        		    		 }
        		    		 else{
        		    			 json.addProperty("code", "2");
            		    		 json.addProperty("msg", "�����ѽ��Ǻ���");
        		    		 }
        		    	 }
        		    	 else{
        		    		 json.addProperty("code", "0");
        		    		 json.addProperty("msg", "����ʧ��");
        		    	 }
        		    	 
        		     }
        		     else if(op.equals("agree")){
        		    	 String friendID=request.getParameter("friendID");
        		    	 json.addProperty("userID", userID);
        		    	 json.addProperty("friendID", friendID);
        		    	 if(friendID!=null){
        		    		 Integer fid=Integer.valueOf(friendID);
        		    		 
        		    		 messageService.agreeBeFriend(id, fid);
        		    		 json.addProperty("code", "1");
        		    		 json.addProperty("msg", "ͬ��ɹ�");
        		    	 }
        		    	 else{
        		    		 json.addProperty("code", "0");
        		    		 json.addProperty("msg", "ͬ��ʧ��");
        		    	 }
        		    	 
        		     }
        		     else if(op.equals("delete")){
        		    	 String friendID=request.getParameter("friendID");
        		    	 if(friendID!=null){
        		    		 Integer fid=Integer.valueOf(friendID);
        		    		 messageService.deleteFriend(id, fid);
        		    		 json.addProperty("code", "1");
        		    		 json.addProperty("msg", "ɾ���ɹ�");
        		    	 }
        		    	 else{
        		    		 json.addProperty("code", "0");
        		    		 json.addProperty("msg", "ɾ���ɹ�");
        		    	 }
        		     }
        		     else{
        		    	 json.addProperty("code", "0");
    		    		 json.addProperty("msg", "ʧ��");
        		     }
        		     
				} catch (Exception e) {
					e.printStackTrace();
					 System.out.println("�쳣");
					 json.addProperty("code", "0");
		    		 json.addProperty("msg", "ʧ��");
				}
            	
             }
             else{
            	 json.addProperty("code", "0");
	    		 json.addProperty("msg", "ʧ��");
             }
             response.setContentType("text/html;charset=utf-8");  
		     response.setCharacterEncoding("UTF-8");  
			 PrintWriter out = response.getWriter(); 
			 
			 System.out.println(json.toString());
		     out.write(json.toString());  
		     out.flush();  
		     out.close(); 
	}

}
