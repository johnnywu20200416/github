<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:useBean id="ordersystem" class="tw.leonchen.jspproject.javabean.TestOrderSystemJavaBean" scope="page"/>

<jsp:setProperty property="customer" name="ordersystem" param="userName"/>
<jsp:setProperty property="shipAddress" name="ordersystem" param="userAddress"/>
<jsp:setProperty property="phone" name="ordersystem" param="userTel"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Process Order</title>
</head>
<body>
  Order Number:<jsp:getProperty property="orderNumber" name="ordersystem"/><br/>
  Customer:<jsp:getProperty property="customer" name="ordersystem"/><br/>
  Ship Address:<jsp:getProperty property="shipAddress" name="ordersystem"/><br/>
  Phone:<jsp:getProperty property="phone" name="ordersystem"/><br/>
</body>
</html>