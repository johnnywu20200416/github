<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
</head>
<body>
  Error Message:<br/>
  <%=exception%><br/>
  
  Error PrintStackTrack:<br/>
  <%
    exception.printStackTrace(new PrintWriter(out));
  %>
</body>
</html>