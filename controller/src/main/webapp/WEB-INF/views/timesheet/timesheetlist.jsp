<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
	<title>Список табелей</title>
	<link href="<c:url value='//static/css/bootstrap.css' />" rel="stylesheet"/>
	<link href="<c:url value='//static/css/app.css' />" rel="stylesheet"/>
    <link href="<c:url value='//static/css/simplePagination.css' />" rel="stylesheet"/>

	<script src="<c:url value='//static/js/jquery-1.11.3.js' />" ></script>
    <script src="<c:url value='//static/js/util.js' />" ></script>
    <script src="<c:url value='//static/js/timesheet/timesheetList.js' />" ></script>
    <script src="<c:url value='//static/js/pagination/jquery.simplePagination.js' />" ></script>
</head>



<body>
<%@include file="../header.jsp" %>
	<div class="generic-container">

		<div class="well">
			<a href='/avectis/timesheet/new'>Добавить новый табель</a>
		</div>
		<div class="panel panel-default">
		  	<div class="panel-heading">
                <span class="lead">Список табелей </span>
                <select size="1" id="itemsOnPage" class="form-control input-sm" >
                    <option>10</option>
                    <option>50</option>
                    <option>100</option>
                </select>
                </div>
			<table class="table table-hover">
	    	</table>
		</div>
        <div id="light-pagination" class="pagination">
        </div>
   	</div>
</body>
<script>
    $( '#itemsOnPage' ).change(function() {
        changeItemsOnPage( this.value);
    });
    $( '#l_states' ).change(function() {
        findByState( this.value);
    });
    $( '#l_contractNumber' ).on("change paste keyup", function() {
        findByContractNumber( this.value);
    });
</script>
</html>