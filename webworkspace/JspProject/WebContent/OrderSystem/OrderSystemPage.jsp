<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order</title>
</head>
<body>
  <h1>Order System</h1>
  <form action="TestOrderSystemProcess.jsp" method="post">
    <p>
	  Name:<input type="text" name="userName"/><br/>
	  Ship Address:<input type="text" name="userAddress"><br/>
	  Tel:<input type="text" name="userTel"><br/>  
  	</p>
  	<input type="submit" value="Login">
  	<input type="reset" value="Clear">
  </form>
</body>
</html>