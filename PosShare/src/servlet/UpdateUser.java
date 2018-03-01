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
import service.UserService;
import utils.Base64Coder;

public class UpdateUser extends HttpServlet {
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
			String userID=request.getParameter("userID");
			String name=request.getParameter("name"); 
			String gender=request.getParameter("gender"); 
			String date=request.getParameter("date"); 
			String headImage=request.getParameter("file"); 
			String headUrl=null;
			int id=Integer.valueOf(userID);
			if(headImage!=null&&(!headImage.equals(""))){
			    headUrl=Base64Coder.getHeadImageURL(userID,request.getSession().getServletContext().getRealPath(""),headImage);
		    }
			userService.updateUser(id, name, gender, date, headUrl);
			User user=userService.findUserByUserID(id);
			json.addProperty("user", gson.toJson(user));
			json.addProperty("code", "1");
			json.addProperty("msg", "修改成功");
			 
		} catch (Exception e) {
			 e.printStackTrace();
			 json.addProperty("code", "0");
			 json.addProperty("msg", "修改失败");
		}
		
	     response.setCharacterEncoding("UTF-8");  
		 PrintWriter out = response.getWriter(); 
		 System.out.println(json.toString());
	     out.write(json.toString());  
	     out.flush();  
	     out.close(); 
		
		
	}

}
