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
        <h1  id="body">添加用户到房间里!</h1>  
        <form action="<%=request.getContextPath()%>/AddUser2Room" id="body" method="post">  
               roomID：<input type="text" value="9308" name="roomID"><br><br>  
               userID：<input type="text" value="3" name="userID"><br><br> 
                userIDs：<input type="text" value="3" name="userIDs"><br><br> 
               操作：<input type="text" value="add" name="op"><br><br>       
            <input type="submit" value="添加">  
            <input type="reset" value="重置">  
        </form>  
    </body>  
</html>  