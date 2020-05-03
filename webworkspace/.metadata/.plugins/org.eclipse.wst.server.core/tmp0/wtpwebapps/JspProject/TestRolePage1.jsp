<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="role" class="tw.leonchen.jspproject.javabean.Role" scope="page" />

<%-- 
  <c:set target="${role}" property="name">${param.myName}</c:set>  的  ${param.myName}可以抓
       從上一頁的 form 的表單送過來的 資料欄位
--%>

<c:set target="${role}" property="name">mike</c:set>
<c:set target="${role}" property="career" value="engineer" />
<c:set target="${role}" property="salary">35000</c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Role Page</title>
</head>
<body>
  Name:${role.name}<br/>
  Career:${role.career}<br/>
  Salary:${role.salary}<br/>
</body>
</html>