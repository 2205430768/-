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
        <h1  id="body">添加记录!</h1>  
        <form action="<%=request.getContextPath()%>/PositionGetAndUpdate" id="body" method="post"> 
              操作：<input type="text" value="update" name="op"><br><br>    
               房间号：<input type="text" value="6" name="roomIds"><br><br> 
               用户ID：<input type="text"  value="5" name="userID"><br><br> 
                位置：<input type="text" value="1,2" name="position"><br><br>  
               结束时间：<input type="text" value="3" name="speed" ><br><br>  
               位置：<input type="text" value="1,2" name="tool"><br><br>  
            
               类型：<input type="text" value="1" name="remainTime"><br><br>
              
               
            <input type="submit" value="提交">  
            <input type="reset" value="重置">  
      </form>  
    </body>  
</html>  