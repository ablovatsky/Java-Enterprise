<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Workers List</title>
	<link href="<c:url value='//static/css/bootstrap.css' />" rel="stylesheet"/>
	<link href="<c:url value='//static/css/app.css' />" rel="stylesheet"/>
    <script src="<c:url value='//static/js/jquery-1.11.3.js' />" ></script>
    <script src="<c:url value='//static/js/worker/workerList.js' />" ></script>
</head>



<body>
	<div class="generic-container">
		<%@include file="authheader.jsp" %>
		<div class="well">
			<a href='/contracts/new-worker'>Add New Worker</a>
		</div>
		<div class="panel panel-default">
		  	<div class="panel-heading"><span class="lead">List of Workers </span></div>
			<table class="table table-hover">
	    	</table>
		</div>
   	</div>
</body>
</html>