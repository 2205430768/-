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
        <h1  id="body">获取好友和房间!</h1>  
        <form action="<%=request.getContextPath()%>/GetFriendAndGroup" id="body" method="post">  
                  用户ID：<input type="text" value="1" name="userID"><br><br>      
           
            <input type="submit" value="获取">  
            <input type="reset" value="重置">  
        </form>  
    </body>  
</html>  