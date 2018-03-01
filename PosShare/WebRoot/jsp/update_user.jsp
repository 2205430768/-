<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<!DOCTYPE html>  
<html>  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>register.JSP Page</title>  
               <style type="text/css">  
#body {  
    background-color: #FFD2BD;  
         text-align:center;  
          alignment-baseline:  middle;  
          text-align-last:  center;  
      }  
      #right{  
          text-align:right;  
      }  
  </style>  
    </head>  
    <body id="body">  
        <h1  id="body">欢迎注册，你将很快完成简单的注册!</h1>  
        <form action="<%=request.getContextPath()%>/UpdateUser" id="body" method="post">  
                   请输入用户名：<input type="text" name="userID"><br><br>  
                  请选择性别：<input type="radio" name="gender" value="boy" checked> 男  
            <input type="radio" name="gender" value="girl">女<br><br>  
         qq：<input type="text" name="qq"><br><br>  
         date：<input type="text" name="date"><br><br> 
         name：<input type="text" name="name"><br><br> 
         file：<input type="text" name="file"><br><br>           
             
            <input type="submit" value="注册">  
            <input type="reset" value="重置">  
        </form>  
    </body>  
</html>  