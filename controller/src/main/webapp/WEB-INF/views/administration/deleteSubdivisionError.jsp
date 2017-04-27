<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Deleting Subdivision</title>
	<link href="<c:url value='//static/css/bootstrap.css' />" rel="stylesheet"/>
	<link href="<c:url value='//static/css/app.css' />" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />

</head>
<body>
	<%@include file="../header.jsp" %>
	<div class="generic-container">
		<div class="authbar">
			<span style=" color: red; font-size: 13px; ">Удаление невозможно. Подразделение содержит работников. Для удаления подразделения, необходимо удалить работников из подразделения.</span>
		</div>
		<span class="well floatRight">
			<a href="<c:url value='/administration/subdivisions' />">Подразделения</a>
		</span>
	</div>
</body>
</html>