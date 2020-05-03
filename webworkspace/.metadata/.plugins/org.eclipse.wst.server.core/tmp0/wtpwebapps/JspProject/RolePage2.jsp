<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="elBean" class="tw.leonchen.jspproject.javabean.Role" scope="page"/>

<c:set target="${elBean}" property="name">${param.userName}</c:set>
<c:set target="${elBean}" property="career" value="${param.userCareer}"/>
<c:set target="${elBean}" property="salary">${param.userSalary}</c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Role Action</title>
</head>
<body>

Name:${elBean.name}<br/>
Career:${elBean.career}<br/>
Salary:${elBean.salary}<br/>

</body>
</html>