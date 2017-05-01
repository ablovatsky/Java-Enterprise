<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>AccessDenied page</title>
	<link href="<c:url value='//static/css/bootstrap.css' />" rel="stylesheet"/>
	<link href="<c:url value='//static/css/app.css' />" rel="stylesheet"/>

</head>
<body>
	<%@include file="header.jsp" %>
	<div class="generic-container">
		<div class="authbar">
			<span style=" color: red; font-size: 13px; ">Недостаточно прав доступа. Обратитесь к системному администратору.</span> </span>
		</div>
	</div>
</body>
</html>