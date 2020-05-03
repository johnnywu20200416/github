<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL</title>
</head>
<body>
  <%
    String msg = "This is a sign.";
    request.setAttribute("message", msg);
  %>
  
  Message:${message}<br/>
  <hr/>
</body>
</html>