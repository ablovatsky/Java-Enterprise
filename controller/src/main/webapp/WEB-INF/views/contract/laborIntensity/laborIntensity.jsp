<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
	<title>Плановая трудоемкость</title>
	<link href="<c:url value='//static/css/bootstrap.css' />" rel="stylesheet"/>
	<link href="<c:url value='//static/css/app.css' />" rel="stylesheet"/>
    <link href="<c:url value='//static/css/simplePagination.css' />" rel="stylesheet"/>

	<script src="<c:url value='//static/js/jquery-1.11.3.js' />" ></script>
    <script src="<c:url value='//static/js/util.js' />" ></script>
    <script src="<c:url value='//static/js/contract/laborIntensity/laborIntensity.js' />" ></script>
    <script src="<c:url value='//static/js/pagination/jquery.simplePagination.js' />" ></script>
</head>



<body>
<%@include file="../../header.jsp" %>
<input type="hidden" id="hdnSession" data-value=<%= session.getAttribute("contractNumber") %> />
	<div class="generic-container">
		<div class="panel panel-default">
		  	<div class="panel-heading">
                <span class="lead">Планавая трудоемкость договора № <%= session.getAttribute("contractNumber") %></span>

            </div>
			<table class="table table-labor-intensity table-hover"></table>
            <table class="table table-new-labor-intensity table-hover"></table>
		</div>
        <div id="light-pagination" class="pagination">
        </div>
   	</div>
</body>
<script>

    $('#plannedLaborIntensity').keypress(function(key) {
        return onePointInInput(key);
    });
    $('#plannedCost').keypress(function(key) {
        return onePointInInput(key);
    });
</script>
</html>