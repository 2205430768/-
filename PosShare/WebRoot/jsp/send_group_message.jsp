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
        <h1  id="body">群消息发送!</h1>  
        <form action="<%=request.getContextPath()%>/SendGroupMessage" id="body" method="post">  
               消息类型：<input type="text" value="1" name="type"><br><br>  
               内容：<input type="text" value="大家好" name="content"><br><br> 
                房间ID：<input type="text" value="9308" name="roomID"><br><br>  
               发送者ID：<input type="text" value="1" name="sendID"><br><br>  
               发送时间：<input type="text" value="334344" name="sendTime"><br><br>
              消息状态：<input type="text" value="1" name="status"><br><br>       
            <input type="submit" value="发送">  
            <input type="reset" value="重置">  
        </form>  
    </body>  
</html>  