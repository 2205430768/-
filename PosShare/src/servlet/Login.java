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

public class Login extends HttpServlet {
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
			request.setCharacterEncoding("utf-8");
		    String username=request.getParameter("username");
		    String password=request.getParameter("password"); 
		   
		    User user=userService.findUser(username, password);
		    if(user!=null){
		    	 json.addProperty("code", "1");
				 json.addProperty("msg", "��¼�ɹ�");
				 json.addProperty("user", gson.toJson(user));
		    }
		    else{
		    	 json.addProperty("code", "0");
				 json.addProperty("msg", "��¼ʧ��");
		    }
		} catch (Exception e) {
			 e.printStackTrace();
			 json.addProperty("code", "0");
			 json.addProperty("msg", "��¼ʧ��");
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
