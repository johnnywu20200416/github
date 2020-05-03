
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HW_Jsp userLogin</title>
</head>
<body>
  <h1>Welcome</h1>
  <form action="TestUserLoginVerifiedServlet2_PlayerJB_New" method="post">
    <p>
      Name:<input type="text" name="userNameForm"/><br/>
      Password:<input type="password" name="userPwdForm"/><br/>
    </p>
    <input type="submit" value="log in"/>
    <input type="reset" value="clear"/>
  </form>
</body>
</html>