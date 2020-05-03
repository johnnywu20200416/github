<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HW_Jsp Click for cards Process</title>
<style>
  th {
    width:150px;
    text-align:center;
    padding-left:10px;
    background-color:lightblue;
  }
  
  td {
    width:150px;
    text-align:center;
    padding-left:15px;
    background-color:yellow;
  }
</style>
</head>
<body>
  <table>
    <tr>
      <th class="y">Your cards:</th>
      <th>Computer's cards:</th>
    </tr>
    
    <tr>
      <td>
        ${playerShow}<!-- 再來要判斷 computer 什麼時候要拿牌、停牌
  					玩家初始化，莊家初始化 => ok
  					比較結果
  					重複玩的迴圈 (判斷balance的部分) 
  					-->
      </td>
      
      <td>
        ${computerShow}
      </td>
    </tr>
  </table>
  
  
</body>
</html>