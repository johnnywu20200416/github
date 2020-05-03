<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Money Check</title>
</head>
<body>
<h1>You have to top up to play</h1>
<h3>$10 per each</h3>
<h3>your balance is: ${playerAtt.balance}</h3><br/>

<form action="TestMoney" method="post">
  How many do you top up:<input type="text" name="money" /><br/>
  <input type="submit" name="checkMoney" value="Top up" />
  <input type="submit" name="checkMoney" value="No" />
</form>
</body>
</html>