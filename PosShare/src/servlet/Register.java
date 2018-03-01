package servlet;

import java.io.File;
import java.io.FileOutputStream;
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
import service.UserService;
import utils.Base64Coder;

public class Register extends HttpServlet {

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
		try {
			String username=request.getParameter("username");
			String password=request.getParameter("password"); 
//			String name=request.getParameter("name"); 
//			String gender=request.getParameter("gender"); 
//			String date=request.getParameter("date"); 
//			String headImage=request.getParameter("file"); 
//			String headUrl=null;
			//System.out.println(headImage);
			
			 User u=new User(username,password);
			 if(userService.hasUser(u)){
				 json.addProperty("code", "0");
				 json.addProperty("msg", "ÕËºÅÒÑ¾­±»×¢²á");
			 }
			 else{
				 /*if(headImage!=null){
					  headUrl=Base64Coder.getHeadImageURL(request.getSession().getServletContext().getRealPath(""),headImage);
					  u.setHead_url(headUrl);
				}*/
				 userService.addUser(u);
				 User user=userService.findUser(username, password);
				 json.addProperty("code", "1");
				 json.addProperty("msg", "×¢²á³É¹¦");
				 json.addProperty("user", gson.toJson(user));
			 }
		} catch (Exception e) {
			 e.printStackTrace();
			 json.addProperty("code", "0");
			 json.addProperty("msg", "×¢²áÊ§°Ü");
		}
		
	     response.setCharacterEncoding("UTF-8");  
		 PrintWriter out = response.getWriter(); 
		 System.out.println(json.toString());
	     out.write(json.toString());  
	     out.flush();  
	     out.close(); 
		
		
		
		
	}

}
