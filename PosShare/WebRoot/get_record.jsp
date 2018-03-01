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
        <h1  id="body">获取记录!</h1>  
        <form action="<%=request.getContextPath()%>/RecordManager" id="body" method="post"> 
              操作：<input type="text" value="get" name="op"><br><br>    
               用户ID：<input type="text" value="5" name="userID"><br><br> 
                 
            <input type="submit" value="提交">  
            <input type="reset" value="重置">  
      </form>  
    </body>  
</html>  