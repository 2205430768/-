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
        <h1  id="body">创建房间!</h1>  
        <form action="<%=request.getContextPath()%>/CreateRoom" id="body" method="post">  
               创建者ID：<input type="text" value="5" name="createrID"><br><br> 
               图像：<input type="text"  name="roomImage"><br><br>   
               创建时间：<input type="text" value="1" name="createTime"><br><br> 
               位置：<input type="text" value="1,2" name="position"><br><br>  
               结束时间：<input type="text" value="3" name="endTime"><br><br>          
               类型：<input type="text" value="1" name="type"><br><br>
              名字：<input type="text" value="徐学逸" name="name"><br><br>       
            <input type="submit" value="创建房间">  
            <input type="reset" value="重置">  
        </form>  
    </body>  
</html>  