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
        <form action="<%=request.getContextPath()%>/Friend" id="body" method="post">  
               用户ID：<input type="text" value="1" name="userID"><br><br>  
               操作：<input type="text" value="agree" name="op"><br><br> 
               朋友ID：<input type="text" value="3" name="friendID"><br><br>         
           
            <input type="submit" value="注册">  
            <input type="reset" value="重置">  
        </form>  
    </body>  
</html>  