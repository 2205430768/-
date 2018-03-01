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
        <form action="<%=request.getContextPath()%>/Login" id="body" method="post">  
            请输入用户名：<input type="text" name="username"><br><br>  
              请输入密码：<input type="password" name="password"><br><br>  
              请选择性别：<input type="radio" name="sex" value="boy" checked> 男  
            <input type="radio" name="sex" value="girl">女<br><br>  
              请选择家乡： <select name="home">  
                <option value="beijing">北京</option>  
                <option value="shanghai">上海</option>  
                <option value="gansu">甘肃</option>  
                <option value="tianjin">天津</option>  
                <option value="chongqing">重庆</option>  
            </select><br><br>  
              请勾选爱好： <input type="checkbox" name="fav" value="sing">唱歌  
            <input type="checkbox" name="fav" value="dance">跳舞  
            <input type="checkbox" name="fav" value="ball">打球  
            <input type="checkbox" name="fav" value="game">玩游戏  
            <input type="checkbox" name="fav" value="chat">聊天  
            <br><br>  
            <input type="submit" value="注册">  
            <input type="reset" value="重置">  
        </form>  
    </body>  
</html>  