<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="TestShoppingCart.jsp"%>

<jsp:useBean id="shopcart" class="tw.leonchen.jspproject.javabean.ShoppingCartJavaBean" scope="session" />
<jsp:setProperty property="mySubmit" name="shopcart" param="submit" />
<jsp:setProperty property="item" name="shopcart" param="product" />

<% shopcart.processRequest(); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Mall</title>
</head>
<body>
  <font size="3" color="#ff0000">
    Your shopping cart list is<br/>
    <% 
      for(String pItem:shopcart.getProductList()){
    	  out.write(pItem + "<br/>");
      }
    %>
  </font>
</body>
</html>