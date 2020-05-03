<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="error/ErrorPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test ErrorPage</title>
</head>
<body>
  <%
    out.write("6/0=" + (6/0));
  %>
</body>
</html>