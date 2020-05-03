<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- TestUserGetCardsServlet -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HW_Jsp Click for cards </title>
</head>
<body>
  <h1>Let's Play BlackJack</h1>
  <h1>Click for Cards...</h1>
  <!-- TestUserGetCardsServlet -->
  <form action="getCardClick2_includeClickProcess.jsp" method=post> 
    Do you want more:<br/>
    <input type="submit" name="getCard" value="Yes"/> <!-- 這裡的yes/no按鈕 還沒做設定???? -->
    <input type="submit" name="getCard" value="No"/>
  </form>
  
  <%@ include file="getCardClickProcess2_beIncluded.jsp" %>
</body>
</html>