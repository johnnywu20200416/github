<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
</head>
<body>
<h2>Result:</h2>
<%@ include file="getCardClickProcess2_beIncluded.jsp" %>

  <c:if test='${gameResult=="computer"}'>
    <font color="red" size="30px">You Lose<br/></font>
  </c:if>
  
  <c:if test='${gameResult=="player"}'>
    <font color="green" size="30px">You Win<br/></font>
  </c:if>
  
  <c:if test='${gameResult=="tied"}'>
    <font color="blue" size="30px">Tied<br/></font>
  </c:if>
  
  your balance remain:${playerAtt.balance}<br/>


  <form action="TestUserLoginVerifiedServlet2_PlayerJB_New" method="post">
    <input type="submit" value="again" />
  </form>
  
  <form action="Logout">
    <input type="submit" value="Log out" />
  </form>

</body>
</html>