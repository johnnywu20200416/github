<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Verified</title>
</head>
<body>
  <form action="TestJndiJdbcConnServletEx2.do" method="post">
    <p>
	  user Name:<input type="text" name="userName"/><br/>
	  user Password:<input type="password" name="userPwd"><br/>  
  	</p>
  	<input type="submit" value="Login">
  	<input type="reset" value="Clear">
  </form>
</body>
</html>