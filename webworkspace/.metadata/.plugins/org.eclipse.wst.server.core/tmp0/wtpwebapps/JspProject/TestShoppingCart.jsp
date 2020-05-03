<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>
</head>
<body>
  <form action="TestShoppingCartProcess.jsp" method="post">
    <h1>Select your product</h1>
    <p>
    <select name="product">
      <option value="harry porter">Harry Porter</option>
      <option value="banana">Banana</option>
      <option value="book">Book</option>
      <option value="flower">Flower</option>
      <option value="chocolate">Chocolate</option>
    </select>
    </p>
    <input type="submit" name="submit" value="add" />
    <input type="submit" name="submit" value="delete" />
    
  </form>
</body>
</html>