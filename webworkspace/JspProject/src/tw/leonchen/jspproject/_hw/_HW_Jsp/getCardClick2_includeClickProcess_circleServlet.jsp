<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- TestUserGetCardsServlet -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HW_Jsp Click for cards </title>
<style>
  p {
    margin-top:0px;
    margin-bottom:0px;
  }
  
  h3.more {
    margin-bottom:0px;
  }
</style>
</head>
<body>

  <h1>Let's Play BlackJack</h1>
  <h3>Click for Cards...</h3>
  <!-- TestUserGetCardsServlet -->
  <form action="TestEachClickSetCardServlet" method=post> 
    <h3 class="more">Do you need more:</h3><br/>
    <p>
      <input type="submit" name="getCard" value="Yes" size="1.5em"/>
      <input type="submit" name="getCard" value="No" size="1.5em"/> <!-- 這裡的 no 按鈕 還沒做設定???? -->
    </p>
  </form>
  
  <br/><br/>
  <%@ include file="getCardClickProcess2_beIncluded.jsp" %>
  
</body>
</html>