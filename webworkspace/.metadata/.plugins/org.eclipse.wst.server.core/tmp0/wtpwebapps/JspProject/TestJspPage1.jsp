<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%! public static final double PI = 3.141592; %>
  
  <%! 
    private int r = 10;
  	public double calCircleArea() {
  		return PI*r*r;
  	}
  %>
  
  <%
    for(int i=1;i<=10;i++){
    	out.write("i= " + i + "<br/>");
    }
  
    double area = calCircleArea();
    out.write("area=" + area + "<br/>");
  %>
  
  Date:<%= new java.util.Date() %><br/>
</body>
</html>